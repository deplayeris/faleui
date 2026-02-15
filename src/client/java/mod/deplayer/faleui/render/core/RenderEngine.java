package mod.deplayer.faleui.render.core;

import net.minecraft.client.gui.GuiGraphics;

/**
 * faleUI渲染引擎核心类
 * 基于原版的1.21.11渲染系统实现
 * 使用GuiGraphics进行安全可靠的渲染
 */
public class RenderEngine {
    private static final RenderEngine INSTANCE = new RenderEngine();
    
    // 私有构造函数确保单例模式
    private RenderEngine() {}
    
    /**
     * 获取渲染引擎实例
     * @return RenderEngine单例实例
     */
    public static RenderEngine getInstance() {
        return INSTANCE;
    }
    
    /**
     * 绘制纯色矩形（最基础和常用的方法）
     * @param graphics GuiGraphics实例
     * @param x X坐标
     * @param y Y坐标
     * @param width 宽度
     * @param height 高度
     * @param color ARGB颜色值
     */
    public void drawRectangle(GuiGraphics graphics, int x, int y, int width, int height, int color) {
        graphics.fill(x, y, x + width, y + height, color);
    }
    
    /**
     * 绘制带边框的矩形
     * @param graphics GuiGraphics实例
     * @param x X坐标
     * @param y Y坐标
     * @param width 宽度
     * @param height 高度
     * @param fillColor 填充颜色
     * @param borderColor 边框颜色
     * @param borderWidth 边框宽度
     */
    public void drawBorderedRectangle(GuiGraphics graphics, int x, int y, int width, int height, 
                                    int fillColor, int borderColor, int borderWidth) {
        // 绘制填充部分
        if (fillColor != 0) {
            graphics.fill(x + borderWidth, y + borderWidth, 
                         x + width - borderWidth, y + height - borderWidth, fillColor);
        }
        
        // 绘制边框
        if (borderWidth > 0 && borderColor != 0) {
            // 上边框
            graphics.fill(x, y, x + width, y + borderWidth, borderColor);
            // 下边框
            graphics.fill(x, y + height - borderWidth, x + width, y + height, borderColor);
            // 左边框
            graphics.fill(x, y, x + borderWidth, y + height, borderColor);
            // 右边框
            graphics.fill(x + width - borderWidth, y, x + width, y + height, borderColor);
        }
    }
    
    /**
     * 绘制线条
     * @param graphics GuiGraphics实例
     * @param x1 起点X坐标
     * @param y1 起点Y坐标
     * @param x2 终点X坐标
     * @param y2 终点Y坐标
     * @param color 颜色值
     * @param thickness 线条粗细
     */
    public void drawLine(GuiGraphics graphics, int x1, int y1, int x2, int y2, int color, int thickness) {
        if (thickness <= 1) {
            // 细线直接绘制
            graphics.fill(x1, y1, x2 + 1, y2 + 1, color);
        } else {
            // 粗线使用矩形模拟
            int dx = x2 - x1;
            int dy = y2 - y1;
            
            if (Math.abs(dx) >= Math.abs(dy)) {
                // 水平主导
                int minY = Math.min(y1, y2) - thickness / 2;
                int maxY = Math.max(y1, y2) + thickness / 2;
                graphics.fill(Math.min(x1, x2), minY, Math.max(x1, x2) + 1, maxY + 1, color);
            } else {
                // 垂直主导
                int minX = Math.min(x1, x2) - thickness / 2;
                int maxX = Math.max(x1, x2) + thickness / 2;
                graphics.fill(minX, Math.min(y1, y2), maxX + 1, Math.max(y1, y2) + 1, color);
            }
        }
    }
    
    /**
     * 绘制圆形（使用近似方法）
     * @param graphics GuiGraphics实例
     * @param centerX 圆心X坐标
     * @param centerY 圆心Y坐标
     * @param radius 半径
     * @param color 颜色值
     */
    public void drawCircle(GuiGraphics graphics, int centerX, int centerY, int radius, int color) {
        // 使用正方形近似圆形（简单实现）
        graphics.fill(centerX - radius, centerY - radius, centerX + radius, centerY + radius, color);
    }
    
    /**
     * 绘制文字
     * @param graphics GuiGraphics实例
     * @param text 文字内容
     * @param x X坐标
     * @param y Y坐标
     * @param color 颜色值
     */
    public void drawText(GuiGraphics graphics, String text, int x, int y, int color) {
        // 这里需要字体渲染器，暂时留空
        // graphics.drawString(font, text, x, y, color);
    }
}