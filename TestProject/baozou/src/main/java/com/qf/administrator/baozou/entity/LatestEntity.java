package com.qf.administrator.baozou.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by L_Alden on 2016/11/14.
 */
public class LatestEntity {

    /**
     * top_stories : [{"document_id":38879,"display_type":1,"title":"《海贼王》很好很好，但剧场版却只是还好","image":"http://ww2.sinaimg.cn/large/006fsAs7jw1f9rqt4mqspj30hs0hswoj.jpg","thumbnail":"http://ww1.sinaimg.cn/large/006fEXxRjw1f9rqtbd1h3j303c03cdg3.jpg","author_avatar":"http://ww4.sinaimg.cn/large/0062abarjw1ewj6e7ay2fj30dw0dwgmh.jpg","author_name":"萌姜女","author_id":541359,"section_id":57,"share_url":"http://baozouribao.com/documents/38879","url":"http://dailyapi.ibaozou.com/api/v31/documents/38879","hit_count":1698,"section_name":"暴走动漫资讯","section_image":"http://ww2.sinaimg.cn/small/00621QEvjw1ewka61577dj30f00a00u5.jpg","section_color":"96D7EE","hit_count_string":"1千","timestamp":1479125424,"comment_count":41,"vote_count":61},{"document_id":38867,"display_type":1,"title":"成龙获奥斯卡终身成就奖，现场虽出了点意外，却不妨碍他成为国人的自豪！","image":"http://ww2.sinaimg.cn/large/0066c0Ztjw1f9rgec4mggj30hs0hsn03.jpg","thumbnail":"http://ww4.sinaimg.cn/large/006eni32jw1f9rge8jyaij303c03c0sn.jpg","author_avatar":"http://ww3.sinaimg.cn/thumb300/0061W1jvgw1f6ta2codxoj305k05k0su.jpg","author_name":"争气战斗姬","author_id":781634,"section_id":58,"share_url":"http://baozouribao.com/documents/38867","url":"http://dailyapi.ibaozou.com/api/v31/documents/38867","hit_count":5616,"section_name":"暴走娱乐资讯","section_image":"http://ww3.sinaimg.cn/small/00621Qfrjw1ewkb8dmt0bj30f00a0q3u.jpg","section_color":"7CD095","hit_count_string":"5千","timestamp":1479125430,"comment_count":257,"vote_count":360},{"document_id":38828,"display_type":1,"title":"《白昼之雨》\u2014\u2014那些存在于周遭的无奈与恐惧","image":"http://ww4.sinaimg.cn/large/006eSyIbjw1f9qjg1tb3zj30hs0hsn00.jpg","thumbnail":"http://ww4.sinaimg.cn/large/006eTIPbjw1f9qjg2973vj303c03cmx3.jpg","author_avatar":"http://ww4.sinaimg.cn/small/0061W1jvjw1f95hoo1m80j31hc0u00xb.jpg","author_name":"鲤食白鹭","author_id":820490,"section_id":18,"share_url":"http://baozouribao.com/documents/38828","url":"http://dailyapi.ibaozou.com/api/v31/documents/38828","hit_count":5076,"section_name":"尼玛看电影","section_image":"http://ww1.sinaimg.cn/small/005YSXYYjw1ewkc3lwx3bj30f00a0q42.jpg","section_color":"F6C471","hit_count_string":"5千","timestamp":1479052507,"comment_count":262,"vote_count":147},{"document_id":38826,"display_type":1,"title":"谁说萌宠大家都爱，这几只我见一次打一次","image":"http://ww3.sinaimg.cn/large/0066c8BUjw1f9qhpnds3gj30hs0hs0t8.jpg","thumbnail":"http://ww3.sinaimg.cn/large/006efN3Djw1f9qhpr9w3cj303c03c3yc.jpg","author_avatar":"http://ww1.sinaimg.cn/sq612/005YDj5ijw1ey412gqxizj30h80pxjse.jpg","author_name":"蛋黄酱","author_id":542307,"section_id":57,"share_url":"http://baozouribao.com/documents/38826","url":"http://dailyapi.ibaozou.com/api/v31/documents/38826","hit_count":10437,"section_name":"暴走动漫资讯","section_image":"http://ww2.sinaimg.cn/small/00621QEvjw1ewka61577dj30f00a00u5.jpg","section_color":"96D7EE","hit_count_string":"1.04万","timestamp":1479052508,"comment_count":110,"vote_count":237},{"document_id":38788,"display_type":1,"title":"大事件幕后花絮，木子旗袍亮相惊艳了时光~","image":"http://ww3.sinaimg.cn/large/005OpYQ4jw1f9phbejjunj30dw099myc.jpg","thumbnail":"http://ww4.sinaimg.cn/large/006eTEWajw1f9phbatm3cj303c03caa8.jpg","author_avatar":"http://ww2.sinaimg.cn/large/005PR2T5jw1f6hjpsi7poj30hs0hst9c.jpg","author_name":"小怪","author_id":561984,"section_id":49,"share_url":"http://baozouribao.com/documents/38788","url":"http://dailyapi.ibaozou.com/api/v31/documents/38788","hit_count":10155,"section_name":"直击暴走大事件","section_image":"http://ww2.sinaimg.cn/small/005ZZu0ujw1ewkbivmf17j30f00a0ab3.jpg","section_color":"80CFCA","hit_count_string":"1.02万","timestamp":1479051015,"comment_count":187,"vote_count":329}]
     * data : [{"document_id":38862,"display_type":2,"title":"美国人，冷静点，别闹了，这挺丢人的","comment_count":10,"vote_count":9,"contribute":0,"timestamp":1479124825,"url":"http://mp.weixin.qq.com/s?__biz=MzA5NzIwMjQzMA==&mid=2649785982&idx=1&sn=fcf85f816792a1a9edc6cdb14c2452bd&chksm=88a05fefbfd7d6f910fbc9f4d0a7d5338bb46a67ff691a087ad1d8aba6cedf06ccb4eac3a97f&scene=0#wechat_redirect","source_name":"北美留学生日报","hit_count":601,"hit_count_string":"601","publish_time":1479124800000,"published_at":"2016-11-14 20:00","recommenders":[{"id":683415,"name":"21世纪","avatar":"http://ww4.sinaimg.cn/large/005OJlaYjw1f0na84yaw1j305k05k3yf.jpg"}],"thumbnail":"http://ww1.sinaimg.cn/large/0066cYItjw1f9rvzeqxy0j306o06odgh.jpg","author_id":683415,"author_name":"21世纪","author_avatar":"http://ww4.sinaimg.cn/large/005OJlaYjw1f0na84yaw1j305k05k3yf.jpg"},{"document_id":38880,"display_type":2,"title":"喂! 不是历来英美好 cp 的么。川普你这剧情， 怎么不按剧本走啊！","comment_count":16,"vote_count":18,"contribute":0,"timestamp":1479121219,"url":"http://www.wezeit.com/wap/305069.html","source_name":"微在","hit_count":2185,"hit_count_string":"2千","publish_time":1479121200000,"published_at":"2016-11-14 19:00","recommenders":[{"id":683365,"name":"腐乳与特仑苏","avatar":"http://ww4.sinaimg.cn/large/005RXNAEjw1f0nau5c37tj305k05kmxb.jpg"}],"thumbnail":"http://ww2.sinaimg.cn/large/006eTaCtjw1f9ru8vghf3j305u05u0sz.jpg","author_id":683365,"author_name":"腐乳与特仑苏","author_avatar":"http://ww4.sinaimg.cn/large/005RXNAEjw1f0nau5c37tj305k05kmxb.jpg"},{"document_id":38863,"display_type":1,"title":"喂，请问您方便面（面）是（试）吗？ 喂？喂？别挂啊！！！","image":"http://ww1.sinaimg.cn/large/0066cRq8jw1f9rsilntf8j30hs0hsgpd.jpg","thumbnail":"http://ww4.sinaimg.cn/large/0066bWFLjw1f9rsily2cgj303c03c3yj.jpg","author_avatar":"http://ww1.sinaimg.cn/large/005Pc3gmjw1f6hjr650kyj305k05kaa4.jpg","author_name":"努力的doge","author_id":681346,"section_id":6,"share_url":"http://baozouribao.com/documents/38863","url":"http://dailyapi.ibaozou.com/api/v31/documents/38863","hit_count":1299,"section_name":"暴走天天看","section_image":"http://ww3.sinaimg.cn/small/0062aa5Kjw1ewkbmqivy0j30f00a0my0.jpg","section_color":"FF9999","hit_count_string":"1千","timestamp":1479125423,"comment_count":31,"vote_count":52},{"document_id":38882,"display_type":1,"title":"你究竟是用办法只好快要变成植物人的兄弟呢，我很好奇！","image":"http://ww2.sinaimg.cn/large/006fsA8Kjw1f9rsj93kxgj30hs0hsgpd.jpg","thumbnail":"http://ww2.sinaimg.cn/large/006fEXMvjw1f9rsj9k5eoj303c03c3yj.jpg","author_avatar":"http://ww1.sinaimg.cn/large/005Pc3gmjw1f6hjr650kyj305k05kaa4.jpg","author_name":"努力的doge","author_id":681346,"section_id":6,"share_url":"http://baozouribao.com/documents/38882","url":"http://dailyapi.ibaozou.com/api/v31/documents/38882","hit_count":999,"section_name":"暴走天天看","section_image":"http://ww3.sinaimg.cn/small/0062aa5Kjw1ewkbmqivy0j30f00a0my0.jpg","section_color":"FF9999","hit_count_string":"999","timestamp":1479125420,"comment_count":17,"vote_count":30},{"document_id":38861,"display_type":2,"title":"\u201c我希望你们以后是去做爱，而不是性交\u201d | 我在美国经历性教育","comment_count":14,"vote_count":31,"contribute":0,"timestamp":1479117623,"url":"http://mp.weixin.qq.com/s?__biz=MzA5NzIwMjQzMA==&mid=2649785982&idx=5&sn=0ca6c0dc1ef3ed2250b4434afb135dd2&chksm=88a05fefbfd7d6f96431a9e3a5a6befedd785fcb38321974e46fdecb1d5c018dce1a6bd6f7e8&scene=0#wechat_redirect","source_name":"北美留学生日报","hit_count":5214,"hit_count_string":"5千","publish_time":1479117600000,"published_at":"2016-11-14 18:00","recommenders":[{"id":683391,"name":"画个圈圈诅咒你","avatar":"http://ww2.sinaimg.cn/large/005O39Jgjw1f0nb426odij305k05kjr6.jpg"}],"thumbnail":"http://ww2.sinaimg.cn/large/0066d5mDjw1f9rsiiav91j306o06oq2z.jpg","author_id":683391,"author_name":"画个圈圈诅咒你","author_avatar":"http://ww2.sinaimg.cn/large/005O39Jgjw1f0nb426odij305k05kjr6.jpg"},{"document_id":38879,"display_type":1,"title":"《海贼王》很好很好，但剧场版却只是还好","image":"http://ww2.sinaimg.cn/large/006fsAs7jw1f9rqt4mqspj30hs0hswoj.jpg","thumbnail":"http://ww1.sinaimg.cn/large/006fEXxRjw1f9rqtbd1h3j303c03cdg3.jpg","author_avatar":"http://ww4.sinaimg.cn/large/0062abarjw1ewj6e7ay2fj30dw0dwgmh.jpg","author_name":"萌姜女","author_id":541359,"section_id":57,"share_url":"http://baozouribao.com/documents/38879","url":"http://dailyapi.ibaozou.com/api/v31/documents/38879","hit_count":1698,"section_name":"暴走动漫资讯","section_image":"http://ww2.sinaimg.cn/small/00621QEvjw1ewka61577dj30f00a00u5.jpg","section_color":"96D7EE","hit_count_string":"1千","timestamp":1479125424,"comment_count":41,"vote_count":61},{"document_id":38878,"display_type":2,"title":"维多利亚时期，姑娘们站着嘿嘿是被禁止的","comment_count":17,"vote_count":55,"contribute":0,"timestamp":1479110423,"url":"http://mp.weixin.qq.com/s?__biz=MjMyMzYyNzg2MA==&mid=2650577784&idx=1&sn=9b2bcb22cd34966ede7d29f5155f6ed1&chksm=ba4873438d3ffa557040f24ed03c391c5f5605bb88f03a02ed7b70a3724686f4c682784c9a3d&scene=0#wechat_redirect","source_name":"","hit_count":9148,"hit_count_string":"9千","publish_time":1479110400000,"published_at":"2016-11-14 16:00","recommenders":[{"id":683343,"name":"我叫没头脑","avatar":"http://ww2.sinaimg.cn/large/005OJ2hXjw1f0naiphlf9j305k05k746.jpg"}],"thumbnail":"http://ww3.sinaimg.cn/large/005O2hDcjw1f9rp1nn0tdj305u05u3yl.jpg","author_id":683343,"author_name":"我叫没头脑","author_avatar":"http://ww2.sinaimg.cn/large/005OJ2hXjw1f0naiphlf9j305k05k746.jpg"},{"document_id":38860,"display_type":2,"title":"老是一个人吃饭的你，遇见过哪些微小但确切的麻烦事？｜征集","comment_count":22,"vote_count":24,"contribute":0,"timestamp":1479108009,"url":"http://mp.weixin.qq.com/s?__biz=MjM5Mzc5NTk1OQ==&mid=2653001275&idx=2&sn=5832ed234c8d44951167f2be6d6e6f04&chksm=bd44ac238a332535fef443e87f3e63bab6e1c456923c555ed5f05c4cb0ab53ac556ea76c78bc&scene=0#wechat_redirect","source_name":"企鹅吃喝指南","hit_count":5940,"hit_count_string":"5千","publish_time":1479106800000,"published_at":"2016-11-14 15:00","recommenders":[{"id":683344,"name":"你胸小别说话","avatar":"http://ww4.sinaimg.cn/large/005PQcfIjw1f0naj7qvgaj305k05ka9z.jpg"}],"thumbnail":"http://ww3.sinaimg.cn/large/006eSN8Ujw1f9rnvsdpmij306o06o0t9.jpg","author_id":683344,"author_name":"你胸小别说话","author_avatar":"http://ww4.sinaimg.cn/large/005PQcfIjw1f0naj7qvgaj305k05ka9z.jpg"},{"document_id":38857,"display_type":2,"title":"和这些座椅搭配，读书也变得更有趣了｜这个设计了不起","comment_count":10,"vote_count":31,"contribute":0,"timestamp":1479103224,"url":"http://mp.weixin.qq.com/s?__biz=MzA3MzU0OTQzMw==&mid=2650199200&idx=4&sn=f4f0b7c4e9f011770044c8695e447a88&chksm=870f27b6b078aea06157b151d3d0fc9306c7aeb084ba9555284ddc7affecdf945568afa69947&scene=0#wechat_redirect","source_name":"好奇心日报","hit_count":6202,"hit_count_string":"6千","publish_time":1479103200000,"published_at":"2016-11-14 14:00","recommenders":[{"id":683405,"name":"下一站滚蛋","avatar":"http://ww3.sinaimg.cn/large/005O1Wjqjw1f0nbcunqx0j305k05k3yn.jpg"}],"thumbnail":"http://ww1.sinaimg.cn/large/006eSA6ujw1f9rlktewo8j306o06ot9a.jpg","author_id":683405,"author_name":"下一站滚蛋","author_avatar":"http://ww3.sinaimg.cn/large/005O1Wjqjw1f0nbcunqx0j305k05k3yn.jpg"},{"document_id":38867,"display_type":1,"title":"成龙获奥斯卡终身成就奖，现场虽出了点意外，却不妨碍他成为国人的自豪！","image":"http://ww2.sinaimg.cn/large/0066c0Ztjw1f9rgec4mggj30hs0hsn03.jpg","thumbnail":"http://ww4.sinaimg.cn/large/006eni32jw1f9rge8jyaij303c03c0sn.jpg","author_avatar":"http://ww3.sinaimg.cn/thumb300/0061W1jvgw1f6ta2codxoj305k05k0su.jpg","author_name":"争气战斗姬","author_id":781634,"section_id":58,"share_url":"http://baozouribao.com/documents/38867","url":"http://dailyapi.ibaozou.com/api/v31/documents/38867","hit_count":5616,"section_name":"暴走娱乐资讯","section_image":"http://ww3.sinaimg.cn/small/00621Qfrjw1ewkb8dmt0bj30f00a0q3u.jpg","section_color":"7CD095","hit_count_string":"5千","timestamp":1479125430,"comment_count":257,"vote_count":360}]
     * timestamp : 1479092400
     */

    private int timestamp;
    /**
     * document_id : 38879
     * display_type : 1
     * title : 《海贼王》很好很好，但剧场版却只是还好
     * image : http://ww2.sinaimg.cn/large/006fsAs7jw1f9rqt4mqspj30hs0hswoj.jpg
     * thumbnail : http://ww1.sinaimg.cn/large/006fEXxRjw1f9rqtbd1h3j303c03cdg3.jpg
     * author_avatar : http://ww4.sinaimg.cn/large/0062abarjw1ewj6e7ay2fj30dw0dwgmh.jpg
     * author_name : 萌姜女
     * author_id : 541359
     * section_id : 57
     * share_url : http://baozouribao.com/documents/38879
     * url : http://dailyapi.ibaozou.com/api/v31/documents/38879
     * hit_count : 1698
     * section_name : 暴走动漫资讯
     * section_image : http://ww2.sinaimg.cn/small/00621QEvjw1ewka61577dj30f00a00u5.jpg
     * section_color : 96D7EE
     * hit_count_string : 1千
     * timestamp : 1479125424
     * comment_count : 41
     * vote_count : 61
     */

    private List<TopStoriesBean> top_stories;
    /**
     * document_id : 38862
     * display_type : 2
     * title : 美国人，冷静点，别闹了，这挺丢人的
     * comment_count : 10
     * vote_count : 9
     * contribute : 0
     * timestamp : 1479124825
     * url : http://mp.weixin.qq.com/s?__biz=MzA5NzIwMjQzMA==&mid=2649785982&idx=1&sn=fcf85f816792a1a9edc6cdb14c2452bd&chksm=88a05fefbfd7d6f910fbc9f4d0a7d5338bb46a67ff691a087ad1d8aba6cedf06ccb4eac3a97f&scene=0#wechat_redirect
     * source_name : 北美留学生日报
     * hit_count : 601
     * hit_count_string : 601
     * publish_time : 1479124800000
     * published_at : 2016-11-14 20:00
     * recommenders : [{"id":683415,"name":"21世纪","avatar":"http://ww4.sinaimg.cn/large/005OJlaYjw1f0na84yaw1j305k05k3yf.jpg"}]
     * thumbnail : http://ww1.sinaimg.cn/large/0066cYItjw1f9rvzeqxy0j306o06odgh.jpg
     * author_id : 683415
     * author_name : 21世纪
     * author_avatar : http://ww4.sinaimg.cn/large/005OJlaYjw1f0na84yaw1j305k05k3yf.jpg
     */

    private List<DataBean> data;

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class TopStoriesBean {
        private int document_id;
        private int display_type;
        private String title;
        private String image;
        private String thumbnail;
        private String author_avatar;
        private String author_name;
        private int author_id;
        private int section_id;
        private String share_url;
        private String url;
        private int hit_count;
        private String section_name;
        private String section_image;
        private String section_color;
        private String hit_count_string;
        private int timestamp;
        private int comment_count;
        private int vote_count;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getAuthor_avatar() {
            return author_avatar;
        }

        public void setAuthor_avatar(String author_avatar) {
            this.author_avatar = author_avatar;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public int getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(int author_id) {
            this.author_id = author_id;
        }

        public int getSection_id() {
            return section_id;
        }

        public void setSection_id(int section_id) {
            this.section_id = section_id;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getHit_count() {
            return hit_count;
        }

        public void setHit_count(int hit_count) {
            this.hit_count = hit_count;
        }

        public String getSection_name() {
            return section_name;
        }

        public void setSection_name(String section_name) {
            this.section_name = section_name;
        }

        public String getSection_image() {
            return section_image;
        }

        public void setSection_image(String section_image) {
            this.section_image = section_image;
        }

        public String getSection_color() {
            return section_color;
        }

        public void setSection_color(String section_color) {
            this.section_color = section_color;
        }

        public String getHit_count_string() {
            return hit_count_string;
        }

        public void setHit_count_string(String hit_count_string) {
            this.hit_count_string = hit_count_string;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
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
    }

    public static class DataBean implements Serializable{
        @Override
        public String toString() {
            return "DataBean{" +
                    "play_count='" + play_count + '\'' +
                    ", file_url='" + file_url + '\'' +
                    ", image='" + image + '\'' +
                    ", document_id=" + document_id +
                    ", display_type=" + display_type +
                    ", title='" + title + '\'' +
                    ", comment_count=" + comment_count +
                    ", vote_count=" + vote_count +
                    ", contribute=" + contribute +
                    ", timestamp=" + timestamp +
                    ", url='" + url + '\'' +
                    ", source_name='" + source_name + '\'' +
                    ", thumbnail='" + thumbnail + '\'' +
                    '}';
        }

        private String play_count;
        private String file_url;
        private String image;
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
        private String section_name;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        private String body;
        public String getPlay_count() {
            return play_count;
        }

        public void setPlay_count(String play_count) {
            this.play_count = play_count;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getFile_url() {
            return file_url;
        }

        public void setFile_url(String file_url) {
            this.file_url = file_url;
        }
        public String getSection_name() {
            return section_name;
        }

        public void setSection_name(String section_name) {
            this.section_name = section_name;
        }

        /**
         * id : 683415
         * name : 21世纪
         * avatar : http://ww4.sinaimg.cn/large/005OJlaYjw1f0na84yaw1j305k05k3yf.jpg
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
