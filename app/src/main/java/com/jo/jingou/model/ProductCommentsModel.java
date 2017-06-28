package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by dfyu on 2017/4/14.
 */
public class ProductCommentsModel extends MyBaseModel {


    /**
     * list : [{"CommentId":181,"Commentcontent":"测试评论","Score":"2.33","Score1":"","Score2":"","Reply":"","Anonymous":0,"Name":"测试","MemberId":4422,"Modifydate":"2017-05-23 15:41:42","MemberPhoto":null,"Model":[{"CommentPhoto":"/assets/uploads/comment/20170523/2017052315414312386.jpg"},{"CommentPhoto":"/assets/uploads/comment/20170523/2017052315414310323.jpg"}]},{"CommentId":179,"Commentcontent":"1255555","Score":"5.0","Score1":"","Score2":"","Reply":"","Anonymous":0,"Name":"测试","MemberId":4422,"Modifydate":"2017-05-23 13:53:07","MemberPhoto":null,"Model":[{"CommentPhoto":"/assets/uploads/comment/20170523/2017052313530803754.jpg"}]},{"CommentId":159,"Commentcontent":"吐","Score":"4.0","Score1":"","Score2":"","Reply":"","Anonymous":0,"Name":"李尚東","MemberId":84,"Modifydate":"2017-05-22 14:39:36","MemberPhoto":"/assets/uploads/20170522/2017052214522792751.jpg","Model":[]},{"CommentId":158,"Commentcontent":"吐","Score":"4.0","Score1":"","Score2":"","Reply":"","Anonymous":0,"Name":"李尚東","MemberId":84,"Modifydate":"2017-05-22 14:39:35","MemberPhoto":"/assets/uploads/20170522/2017052214522792751.jpg","Model":[]}]
     * page : 1
     */

    private int page;
    /**
     * CommentId : 181
     * Commentcontent : 测试评论
     * Score : 2.33
     * Score1 :
     * Score2 :
     * Reply :
     * Anonymous : 0
     * Name : 测试
     * MemberId : 4422
     * Modifydate : 2017-05-23 15:41:42
     * MemberPhoto : null
     * Model : [{"CommentPhoto":"/assets/uploads/comment/20170523/2017052315414312386.jpg"},{"CommentPhoto":"/assets/uploads/comment/20170523/2017052315414310323.jpg"}]
     */

    private List<ListEntity> list;

    public void setPage(int page) {
        this.page = page;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private int CommentId;
        private String Commentcontent;
        private String Score;
        private String Score1;
        private String Score2;
        private String Reply;
        private int Anonymous;
        private String Name;
        private int MemberId;
        private String Modifydate;
        private String MemberPhoto;
        /**
         * CommentPhoto : /assets/uploads/comment/20170523/2017052315414312386.jpg
         */

        private List<ModelEntity> Model;

        public void setCommentId(int CommentId) {
            this.CommentId = CommentId;
        }

        public void setCommentcontent(String Commentcontent) {
            this.Commentcontent = Commentcontent;
        }

        public void setScore(String Score) {
            this.Score = Score;
        }

        public void setScore1(String Score1) {
            this.Score1 = Score1;
        }

        public void setScore2(String Score2) {
            this.Score2 = Score2;
        }

        public void setReply(String Reply) {
            this.Reply = Reply;
        }

        public void setAnonymous(int Anonymous) {
            this.Anonymous = Anonymous;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setMemberId(int MemberId) {
            this.MemberId = MemberId;
        }

        public void setModifydate(String Modifydate) {
            this.Modifydate = Modifydate;
        }

        public void setMemberPhoto(String MemberPhoto) {
            this.MemberPhoto = MemberPhoto;
        }

        public void setModel(List<ModelEntity> Model) {
            this.Model = Model;
        }

        public int getCommentId() {
            return CommentId;
        }

        public String getCommentcontent() {
            return Commentcontent;
        }

        public String getScore() {
            return Score;
        }

        public String getScore1() {
            return Score1;
        }

        public String getScore2() {
            return Score2;
        }

        public String getReply() {
            return Reply;
        }

        public int getAnonymous() {
            return Anonymous;
        }

        public String getName() {
            return Name;
        }

        public int getMemberId() {
            return MemberId;
        }

        public String getModifydate() {
            return Modifydate;
        }

        public String getMemberPhoto() {
            return MemberPhoto;
        }

        public List<ModelEntity> getModel() {
            return Model;
        }

        public static class ModelEntity {
            private String CommentPhoto;

            public void setCommentPhoto(String CommentPhoto) {
                this.CommentPhoto = CommentPhoto;
            }

            public String getCommentPhoto() {
                return CommentPhoto;
            }
        }
    }
}
