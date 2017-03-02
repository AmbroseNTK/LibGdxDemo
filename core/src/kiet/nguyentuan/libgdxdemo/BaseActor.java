package kiet.nguyentuan.libgdxdemo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.compression.lzma.Base;

/**
 * Created by kiettuannguyen on 18/02/2017.
 */

public class BaseActor extends Actor {
    public TextureRegion textureRegion;
    public Rectangle boundary;
    public Vector2 velocity;
    public Polygon boundingPolygon;

    public BaseActor() {
        super();
        textureRegion = new TextureRegion();
        boundary = new Rectangle();
        velocity = new Vector2(0, 0);
        boundingPolygon = null;
    }

    public BaseActor(Texture texture) {
        super();
        textureRegion = new TextureRegion();
        boundary = new Rectangle();
        velocity = new Vector2(0, 0);
        boundingPolygon = null;
        setTexture(texture);
    }

    public void setTexture(Texture texture) {
        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        textureRegion.setRegion(texture);
    }

    public Rectangle getBoundingRectange() {
        boundary.set(getX(), getY(), getWidth(), getHeight());
        return boundary;
    }

    public void act(float dT) {
        super.act(dT);
        moveBy(velocity.x * dT, velocity.y * dT);
    }

    public void draw(Batch batch, float parentAlpha) {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        if (isVisible())
            batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    }

    public void setRectangleBoundary() {
        float w = getWidth();
        float h = getHeight();
        float[] vertices = {0, 0, w, 0, w, h, 0, h};
        boundingPolygon = new Polygon();
        boundingPolygon.setOrigin(getOriginX(), getOriginY());
    }

    public Polygon getBoundingPolygon() {
        boundingPolygon.setPosition(getX(), getY());
        boundingPolygon.setRotation(getRotation());
        return boundingPolygon;
    }

    public boolean overlaps(BaseActor other, boolean resolve) {
        Polygon polygon1 = this.getBoundingPolygon();
        Polygon polygon2 = other.getBoundingPolygon();
        if (!polygon1.getBoundingRectangle().overlaps(polygon2.getBoundingRectangle()))
            return false;
        Intersector.MinimumTranslationVector minimumTranslationVector = new Intersector.MinimumTranslationVector();
        boolean polyOverlap = Intersector.overlapConvexPolygons(polygon1, polygon2, minimumTranslationVector);
        if (polyOverlap && resolve)
            this.moveBy(minimumTranslationVector.normal.x * minimumTranslationVector.depth, minimumTranslationVector.normal.y * minimumTranslationVector.depth);
        float significant = 0.5f;
        return (polyOverlap && (minimumTranslationVector.depth > significant));
    }
    public void copy(BaseActor original){
        this.textureRegion=new TextureRegion(original.textureRegion);
        if(original.boundingPolygon !=null){
            this.boundingPolygon=new Polygon(original.getBoundingPolygon().getVertices());
            this.boundingPolygon.setOrigin(original.getOriginX(),getOriginY());
        }
        this.setPosition(original.getX(),original.getY());
        this.setOrigin(original.getOriginX(),original.getOriginY());
        this.setSize(original.getWidth(),original.getHeight());
        this.setColor(original.getColor());
        this.setVisible(original.isVisible());
    }
    public BaseActor clone(){
        BaseActor newBie=new BaseActor();
        newBie.copy(this);
        return newBie;
    }
}
