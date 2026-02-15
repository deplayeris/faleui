package mod.deplayer.faleui.render.components;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

/**
 * UI组件基类<br>
 * 所有UI组件都应该继承此类，并实现必要的方法<br>
 * 而且我们还内置了一些常规的用法，用于应对基本的的UI事件<br>
 * 继承此类后，只需要实现必要的方法，就可以创建一个完整的UI组件<br>
 * 但是，也只是一个组件，你需要在另一个创建使用类中创建这个组件的实例<br>
 * 然后再进行一些Mixin注入处理，你才能完整的实现你想要达到的效果
 */
public abstract class UIBaseComponent {
    protected int x, y;
    protected int width, height;
    protected boolean visible = true;
    protected boolean enabled = true;
    protected boolean hovered = false;
    protected Component tooltip;
    
    /**
     * 构造函数
     * @param x X坐标
     * @param y Y坐标
     * @param width 宽度
     * @param height 高度
     */
    public UIBaseComponent(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    /**
     * 渲染组件
     * @param graphics GuiGraphics实例
     * @param mouseX 鼠标X坐标
     * @param mouseY 鼠标Y坐标
     * @param delta 时间增量
     */
    public abstract void render(GuiGraphics graphics, int mouseX, int mouseY, float delta);
    
    /**
     * 鼠标点击事件
     * @param mouseX 鼠标X坐标
     * @param mouseY 鼠标Y坐标
     * @param button 鼠标按键
     * @return 是否处理了该事件
     */
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }
    
    /**
     * 鼠标释放事件
     * @param mouseX 鼠标X坐标
     * @param mouseY 鼠标Y坐标
     * @param button 鼠标按键
     */
    public void mouseReleased(double mouseX, double mouseY, int button) {}
    
    /**
     * 鼠标拖拽事件
     * @param mouseX 鼠标X坐标
     * @param mouseY 鼠标Y坐标
     * @param button 鼠标按键
     * @param deltaX X轴位移
     * @param deltaY Y轴位移
     */
    public void mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {}
    
    /**
     * 键盘按下事件
     * @param keyCode 按键代码
     * @param scanCode 扫描代码
     * @param modifiers 修饰键
     * @return 是否处理了该事件
     */
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return false;
    }
    
    /**
     * 字符输入事件
     * @param chr 输入字符
     * @return 是否处理了该事件
     */
    public boolean charTyped(char chr, int modifiers) {
        return false;
    }
    
    /**
     * 检查鼠标是否悬停在组件上
     * @param mouseX 鼠标X坐标
     * @param mouseY 鼠标Y坐标
     * @return 是否悬停
     */
    protected boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }
    
    /**
     * 更新悬停状态
     * @param mouseX 鼠标X坐标
     * @param mouseY 鼠标Y坐标
     */
    public void updateHoverState(int mouseX, int mouseY) {
        this.hovered = isHovered(mouseX, mouseY);
    }
    
    // Getter和Setter方法
    // 用于开发者自行发挥，并且，减轻部分的 UI 的开发负担
    
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    
    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }
    
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    
    public boolean isHovered() { return hovered; }
    
    public Component getTooltip() { return tooltip; }
    public void setTooltip(Component tooltip) { this.tooltip = tooltip; }
    
    /**
     * 获取组件中心点X坐标
     * @return 中心点X坐标
     */
    public int getCenterX() {
        return x + width / 2;
    }
    
    /**
     * 获取组件中心点Y坐标
     * @return 中心点Y坐标
     */
    public int getCenterY() {
        return y + height / 2;
    }
}