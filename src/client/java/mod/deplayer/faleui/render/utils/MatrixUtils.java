package mod.deplayer.faleui.render.utils;

import net.minecraft.util.Mth;
import org.joml.Matrix3x2fStack;
import org.joml.Vector2f;

/**
 * 矩阵变换工具类
 * 提供现代化的2D变换操作
 * 
 * @author Deplayer
 * @since 0.1d
 */
public class MatrixUtils {
    
    /**
     * 应用完整的变换矩阵
     * @param matrices 矩阵栈
     * @param position 位置向量
     * @param scale 缩放向量
     * @param rotation 旋转角度(弧度)
     */
    public static void applyTransformations(Matrix3x2fStack matrices, 
                                          Vector2f position, 
                                          Vector2f scale, 
                                          float rotation) {
        matrices.pushMatrix();
        matrices.translate(position.x, position.y);
        matrices.scale(scale.x, scale.y);
        matrices.rotate(rotation);
    }
    
    /**
     * 应用位置变换
     * @param matrices 矩阵栈
     * @param x X坐标
     * @param y Y坐标
     */
    public static void translate(Matrix3x2fStack matrices, float x, float y) {
        matrices.translate(x, y);
    }
    
    /**
     * 应用缩放变换
     * @param matrices 矩阵栈
     * @param scaleX X轴缩放
     * @param scaleY Y轴缩放
     */
    public static void scale(Matrix3x2fStack matrices, float scaleX, float scaleY) {
        matrices.scale(scaleX, scaleY);
    }
    
    /**
     * 应用旋转变换
     * @param matrices 矩阵栈
     * @param angle 旋转角度(弧度)
     */
    public static void rotate(Matrix3x2fStack matrices, float angle) {
        matrices.rotate(angle);
    }
    
    /**
     * 线性插值
     * @param start 起始值
     * @param end 结束值
     * @param progress 进度(0-1)
     * @return 插值结果
     */
    public static float lerp(float start, float end, float progress) {
        return start + (end - start) * Mth.clamp(progress, 0.0f, 1.0f);
    }
    
    /**
     * 向量线性插值
     * @param start 起始向量
     * @param end 结束向量
     * @param progress 进度(0-1)
     * @return 插值向量
     */
    public static Vector2f lerp(Vector2f start, Vector2f end, float progress) {
        float clampedProgress = Mth.clamp(progress, 0.0f, 1.0f);
        return new Vector2f(
            lerp(start.x, end.x, clampedProgress),
            lerp(start.y, end.y, clampedProgress)
        );
    }
    
    /**
     * 平滑插值(Damping)
     * @param current 当前值
     * @param target 目标值
     * @param deltaTime 时间增量
     * @param smoothness 平滑系数
     * @return 平滑后的值
     */
    public static float smoothDamp(float current, float target, float deltaTime, float smoothness) {
        float t = 1.0f - (float) Math.pow(smoothness, deltaTime);
        return lerp(current, target, t);
    }
    
    /**
     * 弧度转角度
     * @param radians 弧度值
     * @return 角度值
     */
    public static float toDegrees(float radians) {
        return (float) Math.toDegrees(radians);
    }
    
    /**
     * 角度转弧度
     * @param degrees 角度值
     * @return 弧度值
     */
    public static float toRadians(float degrees) {
        return (float) Math.toRadians(degrees);
    }
    
    /**
     * 创建变换矩阵
     * @param position 位置
     * @param scale 缩放
     * @param rotation 旋转
     * @return 变换矩阵
     */
    public static Matrix3x2fStack createTransformationMatrix(Vector2f position, Vector2f scale, float rotation) {
        Matrix3x2fStack matrix = new Matrix3x2fStack();
        applyTransformations(matrix, position, scale, rotation);
        return matrix;
    }
}