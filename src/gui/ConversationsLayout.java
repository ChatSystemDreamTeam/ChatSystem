package gui;

import data.User;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Created by root on 01/12/14.
 */
public class ConversationsLayout extends TabPane{
    public ConversationsLayout(){
        super();
    }


    public void addUser(User u){
        if(!contains(u)){
            ConversationTab tab = new ConversationTab(u);
            this.getTabs().add(tab);

        }
    }

    public void removeUser(User u){

        for(Tab currentTab : getTabs()){
            if(((ConversationTab) currentTab).getUser().equals(u)){
                getTabs().remove(currentTab);
                break;
            }
        }
    }

    public void setFocusUser(User u) {
        this.getSelectionModel().clearAndSelect(getTabIndex(u));
    }


    public int getTabIndex(User u){
        boolean found = false;
        int i = 0;
        for(Tab currentTab : getTabs()){
            if(u.equals(((ConversationTab)currentTab).getUser())){
                found = true;
                break;
            }
            i++;
        }
        if(!found){
            i = -1;
        }
        return i;
    }

    public boolean contains(User u){
        return (getTabIndex(u) != -1);
    }
}
