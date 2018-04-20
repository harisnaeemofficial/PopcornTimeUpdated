package se.popcorn_time.mobile.model.content;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.github.wtekiela.opensub4j.response.MovieInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.functions.Functions;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import se.popcorn_time.base.model.video.info.BasicVideoInfo;
import se.popcorn_time.base.model.video.info.CinemaMoviesInfo;
import se.popcorn_time.base.model.video.info.CinemaTvShowsInfo;
import se.popcorn_time.base.model.video.info.MoviesInfo;
import se.popcorn_time.base.model.video.info.Person;
import se.popcorn_time.base.model.video.info.TvShowsInfo;
import se.popcorn_time.base.model.video.info.VideoInfo;
import se.popcorn_time.base.utils.Logger;
import se.popcorn_time.model.content.IDetailsProvider;
import se.popcorn_time.utils.GsonUtils;

public final class TmdbProvider implements IDetailsProvider {

    private final Api api;
    private final String key;

    public TmdbProvider(@NonNull String url, @NonNull String key) {
        if (TextUtils.isEmpty(url)) {
            this.api = new Api() {
                @Override
                public Observable<Response<ResponseBody>> getTVShowCredits(String id, String apiKey) {
                    return Observable.just(Response.success(ResponseBody.create(MediaType.parse("application/json"),"")));
                }

                @Override
                public Observable<Response<ResponseBody>> getMovieCredits(String imdb, String apiKey) {
                    return Observable.just(Response.success(ResponseBody.create(MediaType.parse("application/json"),"")));
                }

                @Override
                public Observable<Response<ResponseBody>> getTVShowRecommendations(String id, String apiKey) {
                    return Observable.just(Response.success(ResponseBody.create(MediaType.parse("application/json"),"")));
                }

                @Override
                public Observable<Response<ResponseBody>> getMovieRecommendations(String id, String apiKey) {
                    return Observable.just(Response.success(ResponseBody.create(MediaType.parse("application/json"),"")));
                }

                @Override
                public Observable<Response<ResponseBody>> getMovieTmdb(String imdb, String apiKey) {
                    return Observable.just(Response.success(ResponseBody.create(MediaType.parse("application/json"),"")));
                }

                @Override
                public Observable<Response<ResponseBody>> getMovieInfo(@Path("imdb") String imdb, @Query("api_key") String apiKey) {
                    return Observable.just(Response.success(ResponseBody.create(MediaType.parse("application/json"),"")));
                }

                @Override
                public Observable<Response<ResponseBody>> getMovieBackdrops(@Path("imdb") String imdb, @Query("api_key") String apiKey) {
                    return Observable.just(Response.success(ResponseBody.create(MediaType.parse("application/json"),"")));
                }

                @Override
                public Observable<Response<ResponseBody>> search(@Query(value = "query", encoded = true) String title, @Query("api_key") String apiKey) {
                    return Observable.just(Response.success(ResponseBody.create(MediaType.parse("application/json"),"")));
                }

                @Override
                public Observable<Response<ResponseBody>> getTVShowInfo(@Path("id") String id, @Query("api_key") String apiKey) {
                    return Observable.just(Response.success(ResponseBody.create(MediaType.parse("application/json"),"")));
                }

                @Override
                public Observable<Response<ResponseBody>> getTVShowBackdrops(@Path("id") String id, @Query("api_key") String apiKey) {
                    return Observable.just(Response.success(ResponseBody.create(MediaType.parse("application/json"),"")));
                }
            };
        } else {
            this.api = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .baseUrl(url)
                    .build()
                    .create(Api.class);
        }
        this.key = key;
    }

    @Override
    public <T extends VideoInfo> boolean isDetailsExists(T videoInfo) {
        return videoInfo.getDurationMinutes() > 0 && videoInfo.getBackdrops() != null;
    }

    @NonNull
    @Override
    public Observable<? extends VideoInfo> getDetails(final VideoInfo videoInfo) {
        if (videoInfo instanceof CinemaMoviesInfo) {
            return api.getMovieTmdb(videoInfo.getImdb(), key).observeOn(AndroidSchedulers.mainThread()).concatMap(new MoviesTmdbIdRxMapper((MoviesInfo) videoInfo));
        } else if (videoInfo instanceof CinemaTvShowsInfo) {
            return api.search(videoInfo.getTitle(), key).observeOn(AndroidSchedulers.mainThread()).concatMap(new TVShowSearchRxMapper((CinemaTvShowsInfo) videoInfo));
        }
        return Observable.error(new IllegalArgumentException("Wrong video info type"));
    }

    private abstract class VideoInfoRxMapper<T extends VideoInfo> implements Function<Response<ResponseBody>, T> {

        final T videoInfo;

        VideoInfoRxMapper(T videoInfo) {
            this.videoInfo = videoInfo;
        }
    }

    private final class MoviesTmdbIdRxMapper implements Function<Response<ResponseBody>, ObservableSource<MoviesInfo>> {

        MoviesInfo videoInfo;
        MoviesTmdbIdRxMapper(MoviesInfo videoInfo) {
            this.videoInfo = videoInfo;
        }

        @Override
        public ObservableSource<MoviesInfo> apply(@io.reactivex.annotations.NonNull Response<ResponseBody> response) throws Exception {
            if (response.isSuccessful()) {
                final JsonObject jsonInfo = new JsonParser().parse(response.body().charStream()).getAsJsonObject();
                videoInfo.setTmdbID(GsonUtils.getAsInt(jsonInfo, "id"));
                return Observable.merge(
                        api.getMovieInfo(videoInfo.getImdb(), key).map(new MoviesInfoRxMapper((CinemaMoviesInfo) videoInfo)),
                        api.getMovieBackdrops(videoInfo.getImdb(), key).map(new BackdropsRxMapper<>((CinemaMoviesInfo) videoInfo)),
                        api.getMovieCredits(String.valueOf(videoInfo.getTmdbID()), key).map(new MovieCreditsRxMapper((CinemaMoviesInfo) videoInfo)),
                        api.getMovieRecommendations(String.valueOf(videoInfo.getTmdbID()), key).map(new MovieRecommendationsRxMapper((CinemaMoviesInfo) videoInfo)))
                        .observeOn(AndroidSchedulers.mainThread());
            } else {
                Logger.debug("response MoviesInfoRxMapper not successful: "+response.errorBody().string());
            }
            return Observable.just(videoInfo);
        }
    }

    private final class MoviesInfoRxMapper extends VideoInfoRxMapper<MoviesInfo> {

        MoviesInfoRxMapper(MoviesInfo videoInfo) {
            super(videoInfo);
        }

        @Override
        public MoviesInfo apply(@io.reactivex.annotations.NonNull Response<ResponseBody> response) throws Exception {
            if (response.isSuccessful()) {
                final JsonObject jsonInfo = new JsonParser().parse(response.body().charStream()).getAsJsonObject();
                videoInfo.setDurationMinutes(GsonUtils.getAsInt(jsonInfo, "runtime"));
                videoInfo.setTmdbID(GsonUtils.getAsInt(jsonInfo, "id"));
                videoInfo.setBudgetInUSD(GsonUtils.getAsLong(jsonInfo, "budget"));
                videoInfo.setRevenueInUSD(GsonUtils.getAsLong(jsonInfo, "revenue"));
                videoInfo.setHomepage(GsonUtils.getAsString(jsonInfo, "homepage"));

                List<String> productionCountries = new ArrayList<>();
                for (JsonElement productionCountry : jsonInfo.getAsJsonArray("production_countries")) {
                    productionCountries.add(GsonUtils.getAsString((JsonObject) productionCountry, "name"));
                }
                videoInfo.setProductionCountries(productionCountries);

                List<String> productionCompanies = new ArrayList<>();
                for (JsonElement productionCompany : jsonInfo.getAsJsonArray("production_companies")) {
                    productionCompanies.add(GsonUtils.getAsString((JsonObject) productionCompany, "name"));
                }
                videoInfo.setProductionCompanies(productionCompanies);
            } else {
                Logger.debug("response MoviesInfoRxMapper not successful: "+response.errorBody().string());
            }
            return videoInfo;
        }
    }

    private final class TvShowsInfoRxMapper extends VideoInfoRxMapper<TvShowsInfo> {

        TvShowsInfoRxMapper(TvShowsInfo videoInfo) {
            super(videoInfo);
        }

        @Override
        public TvShowsInfo apply(@io.reactivex.annotations.NonNull Response<ResponseBody> response) throws Exception {
            if (response.isSuccessful()) {
                final JsonObject jsonInfo = new JsonParser().parse(response.body().charStream()).getAsJsonObject();
                final JsonArray jsonEpisodeRunTime = jsonInfo.getAsJsonArray("episode_run_time");
                if (jsonEpisodeRunTime.size() > 0) {
                    videoInfo.setDurationMinutes(jsonEpisodeRunTime.get(0).getAsInt());
                }

                videoInfo.setTmdbID(GsonUtils.getAsInt(jsonInfo, "id"));

                videoInfo.setHomepage(GsonUtils.getAsString(jsonInfo, "homepage"));

                List<String> productionCountries = new ArrayList<>();
                for (JsonElement productionCountry : jsonInfo.getAsJsonArray("origin_country")) {
                    Locale loc = new Locale("",productionCountry.getAsString());
                    productionCountries.add(loc.getDisplayCountry());
                }
                videoInfo.setProductionCountries(productionCountries);

                List<Person.CrewMember> creators = new ArrayList<>();
                for (JsonElement creatorJSON : jsonInfo.getAsJsonArray("created_by")) {
                    Person.CrewMember creator = new Person.CrewMember();
                    creator.setProfilePic(GsonUtils.getAsString((JsonObject) creatorJSON, "profile_path"));
                    creator.setJob("Creator");
                    creator.setDepartment("Creators");
                    creator.setName(GsonUtils.getAsString((JsonObject) creatorJSON, "name"));
                    creators.add(creator);
                }
                videoInfo.setCrew(creators);

                List<String> productionCompanies = new ArrayList<>();
                for (JsonElement productionCompany : jsonInfo.getAsJsonArray("production_companies")) {
                    productionCompanies.add(GsonUtils.getAsString((JsonObject) productionCompany, "name"));
                }
                videoInfo.setProductionCompanies(productionCompanies);

            } else {
                Logger.debug("response TvShowsInfoRxMapper not successful: "+response.errorBody().string());
            }
            return videoInfo;
        }
    }

    private final class MovieCreditsRxMapper extends VideoInfoRxMapper<MoviesInfo> {

        MovieCreditsRxMapper(MoviesInfo videoInfo) {
            super(videoInfo);
        }

        @Override
        public MoviesInfo apply(@io.reactivex.annotations.NonNull Response<ResponseBody> response) throws Exception {
            if (response.isSuccessful()) {
                final JsonObject jsonInfo = new JsonParser().parse(response.body().charStream()).getAsJsonObject();

                List<Person.CrewMember> crew = new ArrayList<>();
                for (JsonElement crewMemberJSON : jsonInfo.getAsJsonArray("crew")) {
                    Person.CrewMember crewMember = new Person.CrewMember();
                    crewMember.setProfilePic(GsonUtils.getAsString((JsonObject) crewMemberJSON, "profile_path"));
                    crewMember.setName(GsonUtils.getAsString((JsonObject) crewMemberJSON, "name"));
                    crewMember.setJob(GsonUtils.getAsString((JsonObject) crewMemberJSON, "job"));
                    crewMember.setDepartment(GsonUtils.getAsString((JsonObject) crewMemberJSON, "department"));
                    //Logger.debug("crew of "+videoInfo.getTitle()+": "+crewMember.toString());
                    crew.add(crewMember);
                }
                videoInfo.setCrew(crew);

                List<Person.Actor> actors = new ArrayList<>();
                for (JsonElement actorJSON : jsonInfo.getAsJsonArray("cast")) {
                    Person.Actor actor = new Person.Actor();
                    actor.setProfilePic(GsonUtils.getAsString((JsonObject) actorJSON, "profile_path"));
                    actor.setName(GsonUtils.getAsString((JsonObject) actorJSON, "name"));
                    actor.setCharacter(GsonUtils.getAsString((JsonObject) actorJSON, "character"));
                    //Logger.debug("actor of "+videoInfo.getTitle()+": "+actor.toString());
                    actors.add(actor);
                }
                videoInfo.setCast(actors);
            } else {
                Logger.debug("response MovieCreditsRxMapper not successful(tmdb id: "+videoInfo.getTmdbID()+"): "+response.errorBody().string());
            }
            return videoInfo;
        }
    }

    private final class TvShowsCreditsRxMapper extends VideoInfoRxMapper<TvShowsInfo> {

        TvShowsCreditsRxMapper(TvShowsInfo videoInfo) {
            super(videoInfo);
        }

        @Override
        public TvShowsInfo apply(@io.reactivex.annotations.NonNull Response<ResponseBody> response) throws Exception {
            if (response.isSuccessful()) {
                final JsonObject jsonInfo = new JsonParser().parse(response.body().charStream()).getAsJsonObject();

                List<Person.Actor> actors = new ArrayList<>();
                for (JsonElement actorJSON : jsonInfo.getAsJsonArray("cast")) {
                    Person.Actor actor = new Person.Actor();
                    actor.setProfilePic(GsonUtils.getAsString((JsonObject) actorJSON, "profile_path"));
                    actor.setName(GsonUtils.getAsString((JsonObject) actorJSON, "name"));
                    actor.setCharacter(GsonUtils.getAsString((JsonObject) actorJSON, "character"));
                    //Logger.debug("actor of "+videoInfo.getTitle()+": "+actor.toString());
                    actors.add(actor);
                }
                videoInfo.setCast(actors);
            } else {
                Logger.debug("response TvShowsCreditsRxMapper not successful: "+response.errorBody().string());
            }
            return videoInfo;
        }
    }

    private final class MovieRecommendationsRxMapper extends VideoInfoRxMapper<MoviesInfo> {

        MovieRecommendationsRxMapper(MoviesInfo videoInfo) {
            super(videoInfo);
        }

        @Override
        public MoviesInfo apply(@io.reactivex.annotations.NonNull Response<ResponseBody> response) throws Exception {
            if (response.isSuccessful()) {
                final JsonObject jsonInfo = new JsonParser().parse(response.body().charStream()).getAsJsonObject();

                List<BasicVideoInfo> recommended = new ArrayList<>();
                int maxRecommendations = 20;
                for (JsonElement recommendationJSON : jsonInfo.getAsJsonArray("results")) {
                    maxRecommendations -= 1;
                    if (maxRecommendations <= 0) {
                        break;
                    }
                    BasicVideoInfo recommendation = new BasicVideoInfo();
                    recommendation.setTitle(GsonUtils.getAsString((JsonObject) recommendationJSON, "title"));
                    recommendation.setPoster(GsonUtils.getAsString((JsonObject) recommendationJSON, "poster_path"));
                    recommendation.setDescription(GsonUtils.getAsString((JsonObject) recommendationJSON, "overview"));
                    //Logger.debug("recommendation for "+videoInfo.getTitle()+": "+recommendation.toString());
                    recommended.add(recommendation);
                }
                videoInfo.setRecommendedMOVIESorTVShows(recommended);
            } else {
                Logger.debug("response MovieRecommendationsRxMapper not successful(tmdb id: "+videoInfo.getTmdbID()+"): "+response.errorBody().string());
            }
            return videoInfo;
        }
    }

    private final class TvShowsRecommendationsRxMapper extends VideoInfoRxMapper<TvShowsInfo> {

        TvShowsRecommendationsRxMapper(TvShowsInfo videoInfo) {
            super(videoInfo);
        }

        @Override
        public TvShowsInfo apply(@io.reactivex.annotations.NonNull Response<ResponseBody> response) throws Exception {
            if (response.isSuccessful()) {
                final JsonObject jsonInfo = new JsonParser().parse(response.body().charStream()).getAsJsonObject();

                List<BasicVideoInfo> recommended = new ArrayList<>();
                int maxRecommendations = 20;
                for (JsonElement recommendationJSON : jsonInfo.getAsJsonArray("results")) {
                    maxRecommendations -= 1;
                    if (maxRecommendations <= 0) {
                        break;
                    }
                    BasicVideoInfo recommendation = new BasicVideoInfo();
                    recommendation.setTitle(GsonUtils.getAsString((JsonObject) recommendationJSON, "name"));
                    recommendation.setPoster(GsonUtils.getAsString((JsonObject) recommendationJSON, "poster_path"));
                    recommendation.setDescription(GsonUtils.getAsString((JsonObject) recommendationJSON, "overview"));
                    //Logger.debug("recommendation for "+videoInfo.getTitle()+": "+recommendation.toString());
                    recommended.add(recommendation);
                }
                videoInfo.setRecommendedMOVIESorTVShows(recommended);
            } else {
                Logger.debug("response TvShowsRecommendationsRxMapper not successful: "+response.errorBody().string());
            }
            return videoInfo;
        }
    }

    private final class TVShowSearchRxMapper implements Function<Response<ResponseBody>, ObservableSource<TvShowsInfo>> {

        private final TvShowsInfo videoInfo;

        TVShowSearchRxMapper(TvShowsInfo videoInfo) {
            this.videoInfo = videoInfo;
        }

        @Override
        public ObservableSource<TvShowsInfo> apply(@io.reactivex.annotations.NonNull Response<ResponseBody> responseBodyResponse) throws Exception {
            if (responseBodyResponse.isSuccessful()) {
                final JsonArray jsonResults = new JsonParser().parse(responseBodyResponse.body().charStream()).getAsJsonObject().getAsJsonArray("results");
                if (jsonResults.size() == 1) {
                    return getTvShowsInfo(jsonResults.get(0).getAsJsonObject());
                } else if (jsonResults.size() > 1) {
                    final int year = videoInfo.getYear();
                    if (year > 0) {
                        for (JsonElement jsonElement : jsonResults) {
                            final JsonObject jsonResult = jsonElement.getAsJsonObject();
                            final String date = GsonUtils.getAsString(jsonResult, "first_air_date");
                            if (!TextUtils.isEmpty(date)) {
                                final String[] splitDate = date.split("-");
                                if (splitDate.length == 3 && year == Integer.parseInt(splitDate[0])) {
                                    return getTvShowsInfo(jsonResult);
                                }
                            }
                        }
                    }
                    return getTvShowsInfo(jsonResults.get(0).getAsJsonObject());
                }
                return Observable.just(videoInfo);
            } else {
                Logger.debug("response TVShowSearchRxMapper not successful: "+responseBodyResponse.errorBody().string());
            }
            throw new Exception(responseBodyResponse.errorBody().string());
        }

        private ObservableSource<TvShowsInfo> getTvShowsInfo(@NonNull JsonObject jsonObject) {
            final String id = GsonUtils.getAsString(jsonObject, "id");
            if (TextUtils.isEmpty(id)) {
                return Observable.just(videoInfo);
            }
            return Observable.merge(
                    api.getTVShowInfo(id, key).map(new TvShowsInfoRxMapper(videoInfo)),
                    api.getTVShowCredits(id, key).map(new TvShowsCreditsRxMapper(videoInfo)),
                    api.getTVShowBackdrops(id, key).map(new BackdropsRxMapper<>(videoInfo)),
                    api.getTVShowRecommendations(id, key).map(new TvShowsRecommendationsRxMapper(videoInfo))
            ).observeOn(AndroidSchedulers.mainThread());
        }
    }

    private final class BackdropsRxMapper<T extends VideoInfo> implements Function<Response<ResponseBody>, T> {

        private static final String FORMAT_BACKDROP_URL = "http://image.tmdb.org/t/p/w780%s";

        private final T videoInfo;

        private BackdropsRxMapper(T videoInfo) {
            this.videoInfo = videoInfo;
        }

        @Override
        public T apply(@io.reactivex.annotations.NonNull Response<ResponseBody> responseBodyResponse) throws Exception {
            if (responseBodyResponse.isSuccessful()) {
                final JsonArray jsonBackdrops = new JsonParser().parse(responseBodyResponse.body().charStream()).getAsJsonObject().getAsJsonArray("backdrops");
                final String[] backdrops = new String[jsonBackdrops.size()];
                for (int i = 0; i < jsonBackdrops.size(); i++) {
                    backdrops[i] = String.format(Locale.ENGLISH, FORMAT_BACKDROP_URL, jsonBackdrops.get(i).getAsJsonObject().get("file_path").getAsString());
                }
                videoInfo.setBackdrops(backdrops);
                return videoInfo;
            } else {
                Logger.debug("response BackdropsRxMapper not successful: "+responseBodyResponse.errorBody().string());
            }
            throw new Exception(responseBodyResponse.errorBody().string());
        }
    }

    private interface Api {

        @GET("3/movie/{imdb}")
        Observable<Response<ResponseBody>> getMovieInfo(@Path("imdb") String imdb, @Query("api_key") String apiKey);

        @GET("3/movie/{imdb}/images")
        Observable<Response<ResponseBody>> getMovieBackdrops(@Path("imdb") String imdb, @Query("api_key") String apiKey);

        @GET("3/search/tv")
        Observable<Response<ResponseBody>> search(@Query(value = "query", encoded = true) String title, @Query("api_key") String apiKey);

        @GET("3/tv/{id}")
        Observable<Response<ResponseBody>> getTVShowInfo(@Path("id") String id, @Query("api_key") String apiKey);

        @GET("3/tv/{id}/images")
        Observable<Response<ResponseBody>> getTVShowBackdrops(@Path("id") String id, @Query("api_key") String apiKey);

        //IMDB details api
        @GET("3/tv/{id}/credits")
        Observable<Response<ResponseBody>> getTVShowCredits(@Path("id") String id, @Query("api_key") String apiKey);

        @GET("3/movie/{imdb}/credits")
        Observable<Response<ResponseBody>> getMovieCredits(@Path("imdb") String imdb, @Query("api_key") String apiKey);

        @GET("3/tv/{id}/recommendations")
        Observable<Response<ResponseBody>> getTVShowRecommendations(@Path("id") String id, @Query("api_key") String apiKey);

        @GET("3/movie/{id}/recommendations")
        Observable<Response<ResponseBody>> getMovieRecommendations(@Path("id") String id, @Query("api_key") String apiKey);

        @GET("3/movie/{imdb}/external_ids")
        Observable<Response<ResponseBody>> getMovieTmdb(@Path("imdb") String imdb, @Query("api_key") String apiKey);
    }
}
