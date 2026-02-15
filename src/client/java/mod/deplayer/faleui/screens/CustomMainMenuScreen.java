package mod.deplayer.faleui.screens;

import mod.deplayer.faleui.render.components.UIButton;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
// ResourceLocation导入在1.21.11中可能不需要或路径已改变

public class CustomMainMenuScreen extends TitleScreen {
    private UIButton faleUIButton;
    
    public CustomMainMenuScreen() {
        super();
    }
    
    @Override
    protected void init() {
        super.init();
        
        // 创建faleUI按钮 - 放在屏幕右上角
        int buttonWidth = 100;
        int buttonHeight = 20;
        int buttonX = this.width - buttonWidth - 20; // 距离右边20像素
        int buttonY = 20; // 距离顶部20像素
        
        faleUIButton = new UIButton(
            buttonX, buttonY, 
            buttonWidth, buttonHeight, 
            Component.literal("FALEUI")
        );
        
        // 设置按钮点击事件
        faleUIButton.setOnClick(() -> {
            // 点击后打开faleUI示例界面
            this.minecraft.setScreen(new FaleUIExampleScreen());
        });
        
        // 可以自定义按钮颜色
        faleUIButton.setNormalColor(0xFF2196F3); // 蓝色
        faleUIButton.setHoverColor(0xFF1976D2);  // 深蓝色（悬停时）
    }
    
    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);
        
        // 渲染我们的自定义按钮
        if (faleUIButton != null) {
            faleUIButton.render(graphics, mouseX, mouseY, delta);
        }
    }
    
    // 暂时移除鼠标点击事件处理，避免API兼容性问题
    /*
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // 处理按钮点击事件
        if (faleUIButton != null && faleUIButton.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        
        return super.mouseClicked(mouseX, mouseY, button);
    }
    */
    
    // 暂时移除鼠标释放事件处理，避免API兼容性问题
    /*
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        // 处理按钮释放事件
        if (faleUIButton != null) {
            faleUIButton.mouseReleased(mouseX, mouseY, button);
        }
        
        return super.mouseReleased(mouseX, mouseY, button);
    }
    */
}