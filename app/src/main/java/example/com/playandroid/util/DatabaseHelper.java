package example.com.playandroid.util;

import java.util.List;

import example.com.playandroid.content.search.suggest.SearchHistoryEntity;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/18 21:51
 */
public class DatabaseHelper {
    private Realm realm = Realm.getDefaultInstance();
    public void saveSearchHistory(String keyword) {
        SearchHistoryEntity searchHistory = realm.where(SearchHistoryEntity.class).equalTo("keyword", keyword)
                .findFirst();
        realm.beginTransaction();
        if (searchHistory != null) {
            searchHistory.deleteFromRealm();
        }
        SearchHistoryEntity history = realm.createObject(SearchHistoryEntity.class);
        history.setKeyword(keyword);
        realm.commitTransaction();
    }

    public List<SearchHistoryEntity> querySearchHistory() {
        RealmResults<SearchHistoryEntity> realmResults = realm.where(SearchHistoryEntity.class).findAll();
        return realm.copyFromRealm(realmResults);
    }

    public void deleteSearchHistoryAll() {
        realm.beginTransaction();
        realm.where(SearchHistoryEntity.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }

    public void deleteSearchHistory(String keyword) {
        realm.beginTransaction();
        SearchHistoryEntity searchHistoryEntity = realm
                .where(SearchHistoryEntity.class)
                .equalTo("keyword", keyword)
                .findFirst();
        if (searchHistoryEntity != null) {
            searchHistoryEntity.deleteFromRealm();
        }
        realm.commitTransaction();
    }
}
