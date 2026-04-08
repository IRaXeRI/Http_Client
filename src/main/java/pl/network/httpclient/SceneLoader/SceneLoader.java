package pl.network.httpclient.SceneLoader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.network.httpclient.HelloApplication;

import java.io.IOException;
import java.util.Objects;

public class SceneLoader implements ISceneLoader {

    private static SceneLoader instance;

    protected static SceneLoader getInstance() {
        if (instance == null) {
            instance = new SceneLoader();
        }
        return instance;
    }

    private Stage prepStage(String path) throws ExSceneLoader {
        Stage stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(path));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().addAll();
            stage.setScene(scene);
            return stage;
        } catch (IOException e) {
            throw new ExSceneLoader(e.getMessage());
        }
    }

    @Override
    public Scene loadScene(String path) throws ExSceneLoader {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(path));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().addAll();
            return scene;
        } catch (IOException e) {
            throw new ExSceneLoader("");
        }
    }

    @Override
    public Stage loadStage(String path, boolean resizable) throws ExSceneLoader {
        Stage stage = prepStage(path);
        stage.setResizable(resizable);
        return stage;
    }

    @Override
    public Stage loadStage(String path, boolean resizable, String logoPath) throws ExSceneLoader {
        Stage stage = loadStage(path, resizable);
        stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream(logoPath))));
        return stage;
    }

    @Override
    public Stage loadStage(String path, boolean resizable, String logoPath, StageStyle style) throws ExSceneLoader {
        Stage stage = loadStage(path, resizable, logoPath);
        stage.initStyle(style);
        return stage;
    }

    @Override
    public Parent loadParent(String path) throws ExSceneLoader {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(path));
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new ExSceneLoader("");
        }
    }


}
