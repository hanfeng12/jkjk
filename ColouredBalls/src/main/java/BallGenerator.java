import javafx.scene.paint.Paint;

public class BallGenerator {
    private final Ball prototypeBall;

    public BallGenerator(Ball prototypeBall) {
        this.prototypeBall = prototypeBall;
    }

    public Ball generateBall() {
        Ball newBall = new Ball(
                prototypeBall.getxPos(),
                prototypeBall.getyPos(),
                prototypeBall.getRadius(),
                prototypeBall.getColour()
        );

        newBall.setxVel(Math.random() * 10 - 5);
        newBall.setyVel(Math.random() * 10 - 5);

        return newBall;
    }
}
