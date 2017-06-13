package com.qf.administrator.baozou.entity;

import java.util.List;

/**
 * Created by Alden on 2017/4/17.
 */

public class HotCommentBean {

    /**
     * data : [{"id":3379429,"content":"要不是我家狗把我气的半死我就信了","readable_time":"2017-03-04T10:21:08+08:00","likes":9,"dislikes":0,"time":1488594068000,"score":845676,"own":false,"like":false,"dislike":false,"user":{"id":728511,"name":"北城爱人，","real_avatar_url":"http://q.qlogo.cn/qqapp/1101108234/A39AD1D571844476A464489418F9BC9E/100"},"hottest":true},{"id":3380792,"content":"看了 哭了4次 每个情节都感动人心 每次狗狗死的时候都特别伤心 然后马上重生 发现自己变成了母狗 顿时影院都哄堂大笑 这个影片着实是暖心而不虐心 最后狗狗贝利~贝利~贝利~狗老大又回到了主人身边","readable_time":"2017-03-05T10:22:52+08:00","likes":8,"dislikes":0,"time":1488680572000,"score":829671,"own":false,"like":false,"dislike":false,"user":{"id":512117,"name":"A-2 白い花","real_avatar_url":"http://zhihu.b0.upaiyun.com/avatar/0939cac03"},"hottest":true}]
     * timestamp : 0
     */

    private int timestamp;
    private List<DataBean> data;

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 3379429
         * content : 要不是我家狗把我气的半死我就信了
         * readable_time : 2017-03-04T10:21:08+08:00
         * likes : 9
         * dislikes : 0
         * time : 1488594068000
         * score : 845676
         * own : false
         * like : false
         * dislike : false
         * user : {"id":728511,"name":"北城爱人，","real_avatar_url":"http://q.qlogo.cn/qqapp/1101108234/A39AD1D571844476A464489418F9BC9E/100"}
         * hottest : true
         */

        private int id;
        private String content;
        private String readable_time;
        private int likes;
        private int dislikes;
        private long time;
        private int score;
        private boolean own;
        private boolean like;
        private boolean dislike;
        private UserBean user;
        private boolean hottest;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getReadable_time() {
            return readable_time;
        }

        public void setReadable_time(String readable_time) {
            this.readable_time = readable_time;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public int getDislikes() {
            return dislikes;
        }

        public void setDislikes(int dislikes) {
            this.dislikes = dislikes;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public boolean getOwn() {
            return own;
        }

        public void setOwn(boolean own) {
            this.own = own;
        }

        public boolean getLike() {
            return like;
        }

        public void setLike(boolean like) {
            this.like = like;
        }

        public boolean getDislike() {
            return dislike;
        }

        public void setDislike(boolean dislike) {
            this.dislike = dislike;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public boolean getHottest() {
            return hottest;
        }

        public void setHottest(boolean hottest) {
            this.hottest = hottest;
        }

        public static class UserBean {
            /**
             * id : 728511
             * name : 北城爱人，
             * real_avatar_url : http://q.qlogo.cn/qqapp/1101108234/A39AD1D571844476A464489418F9BC9E/100
             */

            private int id;
            private String name;
            private String real_avatar_url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getReal_avatar_url() {
                return real_avatar_url;
            }

            public void setReal_avatar_url(String real_avatar_url) {
                this.real_avatar_url = real_avatar_url;
            }
        }
    }
}
