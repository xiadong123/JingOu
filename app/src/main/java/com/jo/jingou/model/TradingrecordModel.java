package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4 0004.
 */
public class TradingrecordModel extends MyBaseModel {


    /**
     * CurrentPage : 1
     * Items : [{"Money":"-789.00","Name":"静欧平台","TradeDate":"2017-05-16 18:01:06","Type":0},{"Money":"-1578.00",
     * "Name":"静欧平台","TradeDate":"2017-05-16 18:01:06","Type":0},{"Money":"-9000.00","Name":"静欧平台",
     * "TradeDate":"2017-05-16 17:59:34","Type":0},{"Money":"-9000.00","Name":"静欧平台","TradeDate":"2017-05-16
     * 17:58:07","Type":0},{"Money":"-9000.00","Name":"静欧平台","TradeDate":"2017-05-16 17:37:29","Type":0},
     * {"Money":"-1578.00","Name":"静欧平台","TradeDate":"2017-05-16 14:53:18","Type":0},{"Money":"-200.00",
     * "Name":"静欧平台","TradeDate":"2017-05-15 19:19:28","Type":0},{"Money":"-789.00","Name":"静欧平台",
     * "TradeDate":"2017-05-15 19:19:03","Type":0},{"Money":"-800.00","Name":"静欧平台","TradeDate":"2017-05-15
     * 19:18:50","Type":0},{"Money":"-789.00","Name":"静欧平台","TradeDate":"2017-05-11 11:28:51","Type":0}]
     * PageSize : 10
     * TotalNum : 16
     * TotalPageCount : 2
     */

    private ListBean list;
    /**
     * list : {"CurrentPage":1,"Items":[{"Money":"-789.00","Name":"静欧平台","TradeDate":"2017-05-16 18:01:06","Type":0},
     * {"Money":"-1578.00","Name":"静欧平台","TradeDate":"2017-05-16 18:01:06","Type":0},{"Money":"-9000.00",
     * "Name":"静欧平台","TradeDate":"2017-05-16 17:59:34","Type":0},{"Money":"-9000.00","Name":"静欧平台",
     * "TradeDate":"2017-05-16 17:58:07","Type":0},{"Money":"-9000.00","Name":"静欧平台","TradeDate":"2017-05-16
     * 17:37:29","Type":0},{"Money":"-1578.00","Name":"静欧平台","TradeDate":"2017-05-16 14:53:18","Type":0},
     * {"Money":"-200.00","Name":"静欧平台","TradeDate":"2017-05-15 19:19:28","Type":0},{"Money":"-789.00","Name":"静欧平台",
     * "TradeDate":"2017-05-15 19:19:03","Type":0},{"Money":"-800.00","Name":"静欧平台","TradeDate":"2017-05-15
     * 19:18:50","Type":0},{"Money":"-789.00","Name":"静欧平台","TradeDate":"2017-05-11 11:28:51","Type":0}],
     * "PageSize":10,"TotalNum":16,"TotalPageCount":2}
     * pagesize : 10
     * total : 16
     */

    private int pagesize;
    private int total;

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static class ListBean {
        private int CurrentPage;
        private int PageSize;
        private int TotalNum;
        private int TotalPageCount;
        /**
         * Money : -789.00
         * Name : 静欧平台
         * TradeDate : 2017-05-16 18:01:06
         * Type : 0
         */

        private List<ItemsBean> Items;

        public int getCurrentPage() {
            return CurrentPage;
        }

        public void setCurrentPage(int CurrentPage) {
            this.CurrentPage = CurrentPage;
        }

        public int getPageSize() {
            return PageSize;
        }

        public void setPageSize(int PageSize) {
            this.PageSize = PageSize;
        }

        public int getTotalNum() {
            return TotalNum;
        }

        public void setTotalNum(int TotalNum) {
            this.TotalNum = TotalNum;
        }

        public int getTotalPageCount() {
            return TotalPageCount;
        }

        public void setTotalPageCount(int TotalPageCount) {
            this.TotalPageCount = TotalPageCount;
        }

        public List<ItemsBean> getItems() {
            return Items;
        }

        public void setItems(List<ItemsBean> Items) {
            this.Items = Items;
        }

        public static class ItemsBean {
            private String Money;
            private String Name;
            private String TradeDate;
            private int Type;

            public String getMoney() {
                return Money;
            }

            public void setMoney(String Money) {
                this.Money = Money;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getTradeDate() {
                return TradeDate;
            }

            public void setTradeDate(String TradeDate) {
                this.TradeDate = TradeDate;
            }

            public int getType() {
                return Type;
            }

            public void setType(int Type) {
                this.Type = Type;
            }
        }
    }
}
