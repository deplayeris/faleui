package mod.deplayer.faleui.screens;

import mod.deplayer.faleui.render.components.UIButton;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class FaleUIExampleScreen extends Screen {
    private UIButton backButton;
    private UIButton testButton;
    
    public FaleUIExampleScreen() {
        super(Component.literal("faleUI 示例"));
    }
    
    @Override
    protected void init() {
        super.init();
        
        // 返回按钮
        backButton = new UIButton(
            20, this.height - 40, 
            80, 20, 
            Component.literal("返回")
        );
        backButton.setOnClick(() -> {
            this.minecraft.setScreen(null); // 返回上一个界面
        });
        backButton.setNormalColor(0xFFF44336); // 红色
        
        // 测试按钮
        testButton = new UIButton(
            this.width / 2 - 50, this.height / 2 - 10, 
            100, 20, 
            Component.literal("测试按钮")
        );
        testButton.setOnClick(() -> {
            this.minecraft.player.displayClientMessage(
                Component.literal("faleUI按钮工作正常！"), false
            );
        });
        testButton.setNormalColor(0xFF4CAF50); // 绿色
    }
    
    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        // 渲染半透明背景
        graphics.fill(0, 0, this.width, this.height, 0xCC1A1A2E);
        
        // 渲染标题
        graphics.drawCenteredString(
            this.font, 
            "faleUI 示例界面", 
            this.width / 2, 
            30, 
            0xFFFFFF
        );
        
        // 渲染按钮
        if (backButton != null) {
            backButton.render(graphics, mouseX, mouseY, delta);
        }
        if (testButton != null) {
            testButton.render(graphics, mouseX, mouseY, delta);
        }
        
        super.render(graphics, mouseX, mouseY, delta);
    }
    
    // 暂时移除鼠标点击事件处理，避免API兼容性问题
    /*
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (backButton != null && backButton.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        if (testButton != null && testButton.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        
        return super.mouseClicked(mouseX, mouseY, button);
    }
    */
    
    // 暂时移除鼠标释放事件处理，避免API兼容性问题
    /*
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (backButton != null) {
            backButton.mouseReleased(mouseX, mouseY, button);
        }
        if (testButton != null) {
            testButton.mouseReleased(mouseX, mouseY, button);
        }
        
        return super.mouseReleased(mouseX, mouseY, button);
    }
    */
}