package com.qf.administrator.baozou.entity;

import java.util.List;

/**
 * Created by Alden on 2017/4/17.
 */

public class CommentBean {

    /**
     * data : [{"id":3456048,"content":"【午夜激情看我名字】---【午夜激情看我名字】\n【午夜激情看我名字】---【午夜激情看我名字】\n【午夜激情看我名字】---【午夜激情看我名字】\n【午夜激情看我名字】---【午夜激情看我名字】\n【午夜激情看我名字】---【午夜激情看我名字】\n【午夜激情看我名字】---【午夜激情看我名字】","readable_time":"2017-04-17T20:28:21+08:00","likes":0,"dislikes":0,"time":1492432101000,"score":0,"own":false,"like":false,"dislike":false,"user":{"id":879006,"name":"免费看皇片+Q群609382709","real_avatar_url":"http://q.qlogo.cn/qqapp/1101108234/1AE12FBEE41EEADA39B7AB038E963631/100"},"hottest":false},{"id":3455718,"content":"【午夜激情看我名字】---【午夜激情看我名字】\n【午夜激情看我名字】---【午夜激情看我名字】\n【午夜激情看我名字】---【午夜激情看我名字】\n【午夜激情看我名字】---【午夜激情看我名字】\n【午夜激情看我名字】---【午夜激情看我名字】\n【午夜激情看我名字】---【午夜激情看我名字】","readable_time":"2017-04-17T20:04:45+08:00","likes":0,"dislikes":0,"time":1492430685000,"score":0,"own":false,"like":false,"dislike":false,"user":{"id":879006,"name":"免费看皇片+Q群609382709","real_avatar_url":"http://q.qlogo.cn/qqapp/1101108234/1AE12FBEE41EEADA39B7AB038E963631/100"},"hottest":false},{"id":3453272,"content":"所以全篇其实也是广告","readable_time":"2017-04-16T10:41:49+08:00","likes":0,"dislikes":0,"time":1492310509000,"score":0,"own":false,"like":false,"dislike":false,"user":{"id":682563,"name":"0.0","real_avatar_url":"http://q.qlogo.cn/qqapp/1101108234/2E211364F9636FDE8E843F3A0BDCBE96/100"},"hottest":false},{"id":3452759,"content":"不买唯品会的我错过了么？","readable_time":"2017-04-16T00:57:12+08:00","likes":0,"dislikes":0,"time":1492275432000,"score":0,"own":false,"like":false,"dislike":false,"user":{"id":819124,"name":"deep dream","real_avatar_url":"http://wanzao2.b0.upaiyun.com/baozouribao/f9171320e4a901346985525400063398.png"},"hottest":false},{"id":3451623,"content":"可以的","readable_time":"2017-04-15T17:21:37+08:00","likes":0,"dislikes":0,"time":1492248097000,"score":0,"own":false,"like":false,"dislike":false,"user":{"id":689143,"name":"\u202d\u202d\u202d\u202d\u202d\u202d\u202d","real_avatar_url":"http://q.qlogo.cn/qqapp/1101108234/A6B72866919C676C2253C0B4F9CC1A36/100"},"hottest":false},{"id":3451349,"content":"伦伦，今年的第十五张专辑什么时候出啊？","readable_time":"2017-04-15T12:42:16+08:00","likes":0,"dislikes":0,"time":1492231336000,"score":0,"own":false,"like":false,"dislike":false,"user":{"id":804611,"name":"蒹葭","real_avatar_url":"http://wx.qlogo.cn/mmopen/Q3auHgzwzM5UHCI9wCmwXLhkaYXeREdgs5mKffqCBoJTwdNISrqwVPIrbVxkycfzjc4F0DOqbkgibwHfMzMFURa8A05ZJxEDUSkT0YYhVR58/132"},"hottest":false},{"id":3451336,"content":"收货人确实不真","readable_time":"2017-04-15T12:29:24+08:00","likes":0,"dislikes":0,"time":1492230564000,"score":0,"own":false,"like":false,"dislike":false,"user":{"id":877861,"name":"什么","real_avatar_url":"http://wx.qlogo.cn/mmopen/wcd6Z2zVdtxnMS2QeT3azxHqDZSBAZ84eGDgoJ2LbMp7YNzEBKDRKqI3jg3Ez4Wia2QeiaXtXyUnctkxbJfYAVyFPTkuicYoF48/132"},"hottest":false},{"id":3451295,"content":"朋友，我们来谈一谈中出  哦 不  人生","readable_time":"2017-04-15T11:23:49+08:00","likes":0,"dislikes":0,"time":1492226629000,"score":0,"own":false,"like":false,"dislike":false,"user":{"id":516351,"name":"欲渡无门","real_avatar_url":"http://zhihu.b0.upaiyun.com/avatar/bd843b6fc"},"parent":{"id":3449522,"user_id":654925,"user_name":"我是个坏人"},"hottest":false},{"id":3451273,"content":"震惊！关我卵事","readable_time":"2017-04-15T10:53:34+08:00","likes":0,"dislikes":0,"time":1492224814000,"score":0,"own":false,"like":false,"dislike":false,"user":{"id":297246,"name":"写下你的温柔丶","real_avatar_url":"http://zhihu.b0.upaiyun.com/avatar/25570dd32"},"hottest":false},{"id":3451239,"content":"唯品会给你多少，我京东出两倍","readable_time":"2017-04-15T09:53:08+08:00","likes":1,"dislikes":0,"time":1492221188000,"score":378448,"own":false,"like":false,"dislike":false,"user":{"id":630286,"name":"∥:路亽曱","real_avatar_url":"http://q.qlogo.cn/qqapp/1101108234/DDD165D54EB7411145F9EF8061F941B6/100"},"hottest":false}]
     * timestamp : 1492221188
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
         * id : 3456048
         * content :【看我名字】---【看我名字】
         * readable_time : 2017-04-17T20:28:21+08:00
         * likes : 0
         * dislikes : 0
         * time : 1492432101000
         * score : 0
         * own : false
         * like : false
         * dislike : false
         * user : {"id":879006,"name":"免费看片+Q群609382709","real_avatar_url":"http://q.qlogo.cn/qqapp/1101108234/1AE12FBEE41EEADA39B7AB038E963631/100"}
         * hottest : false
         * parent : {"id":3449522,"user_id":654925,"user_name":"我是个坏人"}
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
        private ParentBean parent;

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

        public ParentBean getParent() {
            return parent;
        }

        public void setParent(ParentBean parent) {
            this.parent = parent;
        }

        public static class UserBean {
            /**
             * id : 879006
             * name : 免费看皇片+Q群609382709
             * real_avatar_url : http://q.qlogo.cn/qqapp/1101108234/1AE12FBEE41EEADA39B7AB038E963631/100
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

        public static class ParentBean {
            /**
             * id : 3449522
             * user_id : 654925
             * user_name : 我是个坏人
             */

            private int id;
            private int user_id;
            private String user_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }
        }
    }
}
