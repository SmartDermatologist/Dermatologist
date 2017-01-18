package dermatologyapp.shahanchor.com.dermatologist.HomeNavigationDrawer;

/**
 * Created by charilj on 12/28/2016.
 */

public class UserNavigationMenuModel {
    private  String title;
    private  String  imageId;

    public UserNavigationMenuModel(String title, String imageId) {
        this.title = title;
        this.imageId = imageId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
