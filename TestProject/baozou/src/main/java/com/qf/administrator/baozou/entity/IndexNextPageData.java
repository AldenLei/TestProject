package com.qf.administrator.baozou.entity;

import java.util.List;

/**
 * Created by L_Alden on 2016/11/15.
 */
public class IndexNextPageData {

    /**
     * data : [{"document_id":38730,"display_type":2,"title":"艺术史上真正的《权利的游戏》 | 意外","comment_count":189,"vote_count":235,"contribute":0,"timestamp":1478779223,"url":"http://mp.weixin.qq.com/s__biz=MjM5OTUzNjE0NA==&mid=2655075433&idx=1&sn=e6ba0426e618ddd309f2393bc7aff69d&chksm=bc8c6f168bfbe6008a5c9a9fa5518c1e0f97341b880fa10ee5935578fa9f1f093ff7cbc1983c&scene=0#wechat_redirect","source_name":"意外艺术","hit_count":24526,"hit_count_string":"2.45万","publish_time":1478779200000,"published_at":"2016-11-10 20:00","recommenders":[{"id":683418,"name":"装腔作势","avatar":"http://ww2.sinaimg.cn/large/005PbapIjw1f0nbex3zbjj305k05kjr9.jpg"}],"thumbnail":"http://ww1.sinaimg.cn/large/0066cVHEjw1f9n9i550dqj306o06o75b.jpg","author_id":683418,"author_name":"装腔作势","author_avatar":"http://ww2.sinaimg.cn/large/005PbapIjw1f0nbex3zbjj305k05kjr9.jpg"},{"document_id":38729,"display_type":2,"title":"最杀马特的教授，恶搞川普希拉里，却是计算机图形学的奇才","comment_count":111,"vote_count":253,"contribute":0,"timestamp":1478772023,"url":"http://mp.weixin.qq.com/s_biz=MzAwNjUyMTA3Mw==&mid=2651061377&idx=1&sn=f8477dfc90649276674724c6bb9cdcf1&chksm=80fb751fb78cfc095b23aa988d4d34631da28e72e3b250d5fea63538a11af40680fe73db83c1&scene=0#wechat_redirect","source_name":"硅谷密探","hit_count":39334,"hit_count_string":"3.93万","publish_time":1478772000000,"published_at":"2016-11-10 18:00","recommenders":[{"id":683346,"name":"民间凹凸曼","avatar":"http://ww1.sinaimg.cn/large/005PQdnwjw1f0nak9roelj305k05kweh.jpg"}],"thumbnail":"http://ww1.sinaimg.cn/large/0066d5s2jw1f9n619xuq9j306o06ogm0.jpg","author_id":683346,"author_name":"民间凹凸曼","author_avatar":"http://ww1.sinaimg.cn/large/005PQdnwjw1f0nak9roelj305k05kweh.jpg"}]
     * timestamp : 1478743200
     */

    private int timestamp;
    /**
     * document_id : 38730
     * display_type : 2
     * title : 艺术史上真正的《权利的游戏》 | 意外
     * comment_count : 189
     * vote_count : 235
     * contribute : 0
     * timestamp : 1478779223
     * url : http://mp.weixin.qq.com/s__biz=MjM5OTUzNjE0NA==&mid=2655075433&idx=1&sn=e6ba0426e618ddd309f2393bc7aff69d&chksm=bc8c6f168bfbe6008a5c9a9fa5518c1e0f97341b880fa10ee5935578fa9f1f093ff7cbc1983c&scene=0#wechat_redirect
     * source_name : 意外艺术
     * hit_count : 24526
     * hit_count_string : 2.45万
     * publish_time : 1478779200000
     * published_at : 2016-11-10 20:00
     * recommenders : [{"id":683418,"name":"装腔作势","avatar":"http://ww2.sinaimg.cn/large/005PbapIjw1f0nbex3zbjj305k05kjr9.jpg"}]
     * thumbnail : http://ww1.sinaimg.cn/large/0066cVHEjw1f9n9i550dqj306o06o75b.jpg
     * author_id : 683418
     * author_name : 装腔作势
     * author_avatar : http://ww2.sinaimg.cn/large/005PbapIjw1f0nbex3zbjj305k05kjr9.jpg
     */

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
        private int document_id;
        private int display_type;
        private String title;
        private int comment_count;
        private int vote_count;
        private int contribute;
        private int timestamp;
        private String url;
        private String source_name;
        private int hit_count;
        private String hit_count_string;
        private long publish_time;
        private String published_at;
        private String thumbnail;
        private int author_id;
        private String author_name;
        private String author_avatar;

        public String getSection_name() {
            return section_name;
        }

        public void setSection_name(String section_name) {
            this.section_name = section_name;
        }

        private String section_name;
        /**
         * id : 683418
         * name : 装腔作势
         * avatar : http://ww2.sinaimg.cn/large/005PbapIjw1f0nbex3zbjj305k05kjr9.jpg
         */

        private List<RecommendersBean> recommenders;

        public int getDocument_id() {
            return document_id;
        }

        public void setDocument_id(int document_id) {
            this.document_id = document_id;
        }

        public int getDisplay_type() {
            return display_type;
        }

        public void setDisplay_type(int display_type) {
            this.display_type = display_type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public int getContribute() {
            return contribute;
        }

        public void setContribute(int contribute) {
            this.contribute = contribute;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSource_name() {
            return source_name;
        }

        public void setSource_name(String source_name) {
            this.source_name = source_name;
        }

        public int getHit_count() {
            return hit_count;
        }

        public void setHit_count(int hit_count) {
            this.hit_count = hit_count;
        }

        public String getHit_count_string() {
            return hit_count_string;
        }

        public void setHit_count_string(String hit_count_string) {
            this.hit_count_string = hit_count_string;
        }

        public long getPublish_time() {
            return publish_time;
        }

        public void setPublish_time(long publish_time) {
            this.publish_time = publish_time;
        }

        public String getPublished_at() {
            return published_at;
        }

        public void setPublished_at(String published_at) {
            this.published_at = published_at;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public int getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(int author_id) {
            this.author_id = author_id;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public String getAuthor_avatar() {
            return author_avatar;
        }

        public void setAuthor_avatar(String author_avatar) {
            this.author_avatar = author_avatar;
        }

        public List<RecommendersBean> getRecommenders() {
            return recommenders;
        }

        public void setRecommenders(List<RecommendersBean> recommenders) {
            this.recommenders = recommenders;
        }

        public static class RecommendersBean {
            private int id;
            private String name;
            private String avatar;

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

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }
    }
}
