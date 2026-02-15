package mod.deplayer.faleui.render.components;

import mod.deplayer.faleui.render.core.RenderEngine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;

/**
 * 按钮UI组件<br>
 * 支持点击、悬停等交互效果<br>
 * 同样继承自基类UIBaseComponent<br>
 * 实际上，这里面的有些颜色需要你自己去做，所以你需要把它继承或者使用这里给出的改颜色的Method
 * 并且修改颜色
 * 这里已做了一些处理，以至于你只需要继承或者使用改颜色的方法，然后改颜色值<br>
 * 至于这里的颜色只是一个什么样的格式？详见 ARGB 文档，这也是Minecraft默认用的颜色表达方式
 */
public class UIButton extends UIBaseComponent {
    private Component text;
    private Runnable onClickAction;
    private boolean pressed = false;
    private int normalColor = 0xFF404040;
    private int hoverColor = 0xFF606060;
    private int pressedColor = 0xFF202020;
    private int textColor = 0xFFFFFFFF;
    int borderColor = hovered ? 0xFFAAAAAA : 0xFF888888;
    
    /**
     * 构造函数，创建UIButton实例对象，也就是创建一个按钮
     * @param x X坐标
     * @param y Y坐标
     * @param width 宽度
     * @param height 高度
     * @param text 按钮文本
     */
    public UIButton(int x, int y, int width, int height, Component text) {
        super(x, y, width, height);
        this.text = text;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        if (!visible) return;
        
        updateHoverState(mouseX, mouseY);
        
        // 选择背景颜色
        int backgroundColor = normalColor;
        if (pressed) {
            backgroundColor = pressedColor;
        } else if (hovered) {
            backgroundColor = hoverColor;
        }
        
        // 渲染背景
        RenderEngine.getInstance().drawRectangle(graphics, x, y, width, height, backgroundColor);
        
        // 渲染边框
        renderBorder(graphics);
        
        // 渲染文本
        if (text != null) {
            int textX = getCenterX();
            int textY = getCenterY() - 4; // 这里是为了文本垂直居中调整看，不然文本会偏上一点，不好看
            
            graphics.drawCenteredString(
                Minecraft.getInstance().font,
                text,
                textX,
                textY,
                textColor
            );
        }
    }
    
    /**
     * 渲染按钮边框
     */
    private void renderBorder(GuiGraphics graphics) {
        
        // 上边框
        graphics.fill(x, y, x + width, y + 1, borderColor);
        // 下边框
        graphics.fill(x, y + height - 1, x + width, y + height, borderColor);
        // 左边框
        graphics.fill(x, y, x + 1, y + height, borderColor);
        // 右边框
        graphics.fill(x + width - 1, y, x + width, y + height, borderColor);
    }

    //下面都是按钮的UI事件了

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!enabled || !visible) return false;
        
        if (isHovered((int)mouseX, (int)mouseY)) {
            pressed = true;
            playClickSound();
            return true;
        }
        return false;
    }
    
    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (pressed && isHovered((int)mouseX, (int)mouseY)) {
            if (onClickAction != null) {
                onClickAction.run();
            }
        }
        pressed = false;
    }
    
    /**
     * 播放点击音效
     */
    private void playClickSound() {
        Minecraft.getInstance().getSoundManager().play(
            net.minecraft.client.resources.sounds.SimpleSoundInstance.forUI(
                SoundEvents.UI_BUTTON_CLICK, 1.0F
            )
        );
    }
    
    // Getter和Setter方法
    
    public Component getText() { return text; }
    public void setText(Component text) { this.text = text; }
    
    public void setOnClick(Runnable action) { this.onClickAction = action; }
    
    public int getNormalColor() { return normalColor; }
    public void setNormalColor(int normalColor) { this.normalColor = normalColor; }
    
    public int getHoverColor() { return hoverColor; }
    public void setHoverColor(int hoverColor) { this.hoverColor = hoverColor; }
    
    public int getPressedColor() { return pressedColor; }
    public void setPressedColor(int pressedColor) { this.pressedColor = pressedColor; }
    
    public int getTextColor() { return textColor; }
    public void setTextColor(int textColor) { this.textColor = textColor; }
    
    public boolean isPressed() { return pressed; }
}