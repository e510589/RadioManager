package hf.yz.hfradiomanager_v2.utils.Data;

import java.util.List;

public interface DataSource {

    interface OnDataLoadedCallBack {

        void OnDataLoaded(List<DataItem> item);

        void OnDataNotAvailable();

    }

    interface OnDataGetCallBack {

        void OnDataGet(DataItem item);

        void OnDataNotAvailable();

    }

    void getAllData(OnDataLoadedCallBack callBack);

    void getData(String dataID, int index, OnDataGetCallBack callBack);

    void deleteAllData();

    void deleteData(String dataID, int index);

    void saveData(DataItem item);
}
