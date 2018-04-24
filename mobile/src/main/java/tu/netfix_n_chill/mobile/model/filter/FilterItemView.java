package tu.netfix_n_chill.mobile.model.filter;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import tu.netfix_n_chill.model.filter.IFilterItem;

public final class FilterItemView implements IFilterItem {

    private final int viewName;
    private final IFilterItem filterItem;

    public FilterItemView(@StringRes int viewName, @NonNull IFilterItem filterItem) {
        this.viewName = viewName;
        this.filterItem = filterItem;
    }

    @NonNull
    @Override
    public String getValue() {
        return filterItem.getValue();
    }

    @StringRes
    public int getViewName() {
        return viewName;
    }
}
