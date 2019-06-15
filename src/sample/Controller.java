package sample;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.sql.*;



public class Controller {

    public Button start;
    public Button create;
    public  Button exit;
    public Button results;
    public Button next;
    public  Button view;
    public  Button vnext;

    public Label postname;
    public Label lb1;
    public Label lb2;
    public Label lb3;
    public Label lb4;
    public Label pn1;

    public Button name1;
    public Button name2;
    public Button name3;
    public Button name4;
    public Button startnext;

    public TextField getname1;
    public TextField getname2;
    public TextField getname3;
    public TextField getname4;
    public TextField getpostname;

    public Label vpn1;
    public Label vlb1;
    public Label vlb2;
    public Label vlb3;
    public Label vlb4;


    public static final String DB_NAME = "names.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Atharva\\IdeaProjects\\elections\\" + DB_NAME;
    private Connection conn;
    public ResultSet resultSet;

    Statement statement;
    public void process(ActionEvent event) throws Exception{

        if(event.getSource() == start){
            start.setVisible(false);
            create.setVisible(false);
            results.setVisible(false);
            exit.setVisible(false);

            view.setVisible(false);
            startprocess();
        }
        if(event.getSource() == create){

            createprocess();
        }

        if(event.getSource() == view){


            viewprocess();
        }



    }



    public void createprocess() throws Exception {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
           statement = conn.createStatement();
        }catch (SQLException e)
        {
            System.out.println("ERROR:" + e.getMessage());
        }
        getpostname.setVisible(true);
        getname1.setVisible(true);
        getname2.setVisible(true);
        getname3.setVisible(true);
        getname4.setVisible(true);

        pn1.setVisible(true);
        lb1.setVisible(true);
        lb2.setVisible(true);
        lb3.setVisible(true);
        lb4.setVisible(true);

        start.setVisible(false);
        create.setVisible(false);
        results.setVisible(false);
        exit.setVisible(false);
        view.setVisible(false);
        next.setVisible(true);





    }

    public void add() throws Exception{
        try {

            statement.execute("create table if not exists elections (postname text, name text, count int)");
            statement.execute("insert into elections  values ( '" +  getpostname.getText() + "' , '" + getname1.getText()+"', 0" +" )");
            statement.execute("insert into elections  values ( '" +  getpostname.getText() + "' , '" + getname2.getText() +"', 0" +" )");
            statement.execute("insert into elections  values ( '" +  getpostname.getText() + "' , '" + getname3.getText() +"', 0" +" )");
            statement.execute("insert into elections  values ( '" +  getpostname.getText() + "' , '" + getname4.getText() +"', 0" +" )");

            getpostname.clear();
            getname1.clear();
            getname2.clear();
            getname3.clear();
            getname4.clear();
        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }
    }

    public void startprocess() throws Exception {
        postname.setVisible(true);

        startnext.setVisible(true);
       /* name1.setVisible(true);
        name2.setVisible(true);
        name3.setVisible(true);
        name4.setVisible(true);*/
        try{

            conn = DriverManager.getConnection(CONNECTION_STRING);
            statement = conn.createStatement();

        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }
        votenext();
    }

    public void votenext() throws Exception{
        try {
            //if(resultSet == null)
                resultSet = statement.executeQuery("select postname,name from elections ");

            resultSet.next();
            postname.setText(resultSet.getString("postname"));

            if(resultSet.getString("name") != " ") {
                name1.setVisible(true);
                name1.setText(resultSet.getString("name"));
                resultSet.next();
            }
            name2.disableProperty();
            if(resultSet.getString("name") != " ") {
                name2.setVisible(true);
                name2.setText(resultSet.getString("name"));
                resultSet.next();
            }

            if(resultSet.getString("name") != " ") {
                name3.setVisible(true);
                name3.setText(resultSet.getString("name"));
                resultSet.next();
            }

            if(resultSet.getString("name") != "zzz") {
                name4.setVisible(true);
                name4.setText(resultSet.getString("name"));
            }


        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }

    }

    public void viewprocess() throws  Exception{

        view.setVisible(false);
        vpn1.setVisible(true);
        vlb1.setVisible(true);
        vlb2.setVisible(true);
        vlb3.setVisible(true);
        vlb3.setVisible(true);
        vnext.setVisible(true);
        start.setVisible(false);
        create.setVisible(false);
        results.setVisible(false);
        exit.setVisible(false);
        try{

            conn = DriverManager.getConnection(CONNECTION_STRING);
            statement = conn.createStatement();

        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }
        view();

    }
    public void view() throws Exception{
        try {


           // if(resultSet == null)
                 resultSet = statement.executeQuery("select postname,name from elections ");

            resultSet.next();
            vpn1.setText(resultSet.getString("postname"));

            vlb1.setText(resultSet.getString("name"));
            resultSet.next();

                vlb2.setText(resultSet.getString("name"));
                resultSet.next();


                vlb3.setText(resultSet.getString("name"));
                resultSet.next();


                vlb4.setText(resultSet.getString("name"));






        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }


    }

    public void gohome(){
        start.setVisible(true);
        create.setVisible(true);
        results.setVisible(true);
        exit.setVisible(true);
        view.setVisible(true);
        vpn1.setVisible(false);
        vlb1.setVisible(false);
        vlb2.setVisible(false);
        vlb3.setVisible(false);
        vlb3.setVisible(false);

        getpostname.setVisible(false);
        getname1.setVisible(false);
        getname2.setVisible(false);
        getname3.setVisible(false);
        getname4.setVisible(false);

        pn1.setVisible(false);
        lb1.setVisible(false);
        lb2.setVisible(false);
        lb3.setVisible(false);
        lb4.setVisible(false);
        startnext.setVisible(false);
        vnext.setVisible(false);
        next.setVisible(false);
        postname.setVisible(false);
    }
}
