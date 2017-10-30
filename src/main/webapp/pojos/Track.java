package webapp.pojos;

/**
 * Created by Makar Kalancha
 * Date: 22 Nov 2016
 * Time: 14:01
 */
public class Track {

    String title;
    String singer;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "Track [title=" + title + ", singer=" + singer + "]";
    }

}
