package pl.network.httpclient.SceneLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public interface ISceneLoader {

    public static ISceneLoader getInstance(){ return SceneLoader.getInstance(); }
    public Scene loadScene(String path) throws ExSceneLoader;
    public Stage loadStage(String path, boolean resizable) throws ExSceneLoader;
    public Stage loadStage(String path, boolean resizable, String logoPath) throws ExSceneLoader;
    public Stage loadStage(String path, boolean resizable, String logoPath, StageStyle style) throws ExSceneLoader;
    public Parent loadParent(String path) throws ExSceneLoader;
}
