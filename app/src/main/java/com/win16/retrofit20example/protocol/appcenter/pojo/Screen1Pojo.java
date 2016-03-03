package com.win16.retrofit20example.protocol.appcenter.pojo;

import java.util.List;

/**
 * @author REXZOU
 * @Description:
 * @date 2016/2/23 11:22
 * @copyright TCL-MIG
 */
public class Screen1Pojo {



    public String status;
    public String msg;


    public DataEntity data;
    public int compress;

    public static class DataEntity {
        public int ntof;

        public List<AppsEntity> apps;

        public static class AppsEntity {
            public int id;
            public String name;
            public int priority;
            public String appPkg;
            public String size;
            public String iconUrl;
            public String downloadUrl;
            public String summary;
            public int sourceFrom;
            public String version;
            public String developer;
            public int star;
            public int dldTimes;
            public String bannerUrl;
            public String screenShots;
            public long versionChangedOn;
            public String description;
            public Object relateApps;
            public int charge;
            public int buy;
            public int dldLimit;
            public String shareDldUrl;
            public Object pays;
        }
    }
}
