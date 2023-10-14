import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

class GameWindow {
    private final GraphicsContext gc;
    private Scene scene;
    private BallPit model;
    private BallGenerator ballGenerator;

    GameWindow(BallPit model) {
        this.model = model;

        // 创建一个原型 Ball 对象
        Ball prototypeBall = new Ball(100, 100, 20, Paint.valueOf("RED"));

        // 创建 BallGenerator 对象
        this.ballGenerator = new BallGenerator(prototypeBall);

        Pane pane = new Pane();
        this.scene = new Scene(pane, model.getWidth(), model.getHeight());
        Canvas canvas = new Canvas(model.getWidth(), model.getHeight());
        gc = canvas.getGraphicsContext2D();
        pane.getChildren().add(canvas);

        // 创建并配置按钮
        Button generateBallButton = new Button("Generate Ball");
        generateBallButton.setLayoutX(10); // 设置按钮的X位置
        generateBallButton.setLayoutY(10); // 设置按钮的Y位置
        generateBallButton.setOnAction(e -> {
            Ball newBall = ballGenerator.generateBall();
            model.addBall(newBall);
        });

        // 将按钮添加到面板
        pane.getChildren().add(generateBallButton);
    }

    Scene getScene() {
        return this.scene;
    }

    void run() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17),
                t -> this.draw()));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void draw() {
        model.tick();

        gc.clearRect(0, 0, model.getWidth(), model.getHeight());

        for (Ball ball: model.getBalls()) {
            gc.setFill(ball.getColour());
            gc.fillOval(ball.getxPos() - ball.getRadius(),
                    ball.getyPos() - ball.getRadius(),
                    ball.getRadius() * 2,
                    ball.getRadius() * 2);
        }
    }
}
