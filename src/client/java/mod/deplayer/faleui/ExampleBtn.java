package mod.deplayer.faleui;

import mod.deplayer.faleui.render.components.UIButton;
import mod.deplayer.faleui.screens.FaleUIExampleScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

/**
 * 示范的按钮创建使用类，用于在主菜单显示faleUI按钮<br>
 * 这个类包装了UIButton组件，提供了简单的API供外部使用<br>
 * 你也可以直接用它作为你自己的代码，我没意见<br>
 * 然而，实际上，这个示例只能创建一种风格的按钮<br>
 * 关于如何使用这个按钮创建使用类，现在请你转向src/client/java/mod/deplayer/faleui/mixin/client/ExampleClientMixin.java<br>
 * 他需要使用Mixin来注入到主屏幕中
 * 而且你要小心啊
 * 稍不注意就可能注入失败,但是如果你学会了，那么将如鱼得水
 */
public class ExampleBtn {
    private UIButton btn;
    
    /**
     * 构造函数，创建示例按钮
     * @param x 按钮X坐标
     * @param y 按钮Y坐标
     * @param width 按钮宽度
     * @param height 按钮高度
     */
    public ExampleBtn(int x, int y, int width, int height) {
        // 创建UIButton实例
        btn = new UIButton(x, y, width, height, Component.literal("FALEUI"));
        
        // 设置按钮点击事件
        btn.setOnClick(() -> {
            System.out.println("[faleUI] 按钮被点击!");
            // 打开faleUI示例界面
            Minecraft.getInstance().setScreen(new FaleUIExampleScreen());
        });
        
        // 自定义按钮样式
        btn.setNormalColor(0xFF2196F3);  // 蓝色
        btn.setHoverColor(0xFF1976D2);   // 深蓝色（悬停时）
        btn.setPressedColor(0xFF0D47A1); // 更深的蓝色（按下时）
        btn.setTextColor(0xFFFFFFFF);    // 白色文字
        
        System.out.println("[faleUI] ExampleBtn已创建，位置: (" + x + ", " + y + ")");
    }
    
    /**
     * 渲染按钮
     * @param graphics GuiGraphics实例
     * @param mouseX 鼠标X坐标
     * @param mouseY 鼠标Y坐标
     * @param delta 时间增量
     */
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        if (btn != null && btn.isVisible()) {
            btn.render(graphics, mouseX, mouseY, delta);
        }
    }
    
    /**
     * 处理鼠标点击事件
     * @param mouseX 鼠标X坐标
     * @param mouseY 鼠标Y坐标
     * @param button 鼠标按键
     * @return 是否处理了该事件
     */
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (btn != null && btn.isEnabled() && btn.isVisible()) {
            return btn.mouseClicked(mouseX, mouseY, button);
        }
        return false;
    }
    
    /**
     * 处理鼠标释放事件
     * @param mouseX 鼠标X坐标
     * @param mouseY 鼠标Y坐标
     * @param button 鼠标按键
     */
    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (btn != null) {
            btn.mouseReleased(mouseX, mouseY, button);
        }
    }
    
    // Getter方法
    public UIButton getButton() { return btn; }
    public int getX() { return btn.getX(); }
    public int getY() { return btn.getY(); }
    public int getWidth() { return btn.getWidth(); }
    public int getHeight() { return btn.getHeight(); }
}