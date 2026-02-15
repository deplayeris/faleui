package mod.deplayer.faleui.mixin.client;

import mod.deplayer.faleui.ExampleBtn;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
*    示例按钮的注入<br>
*    你在阅读示例的时候，也可以借助这个来阅读如何在主屏幕添加 GUI<br>
 * 然后的话还要在src/client/resources/faleui.client.json做一些处理，如果你自己开发，你可以参照我这个看看如何做处理
 */
@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    private ExampleBtn faleUIButton;
    
    /**
     * 在游戏主屏幕初始化时创建faleUI按钮
     * @param ci 回调信息
     */
    @Inject(method = "init", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        TitleScreen screen = (TitleScreen) (Object) this;
        int buttonWidth = 100;
        int buttonHeight = 20;
        int buttonX = screen.width - buttonWidth - 20;
        int buttonY = 20;
        
        faleUIButton = new ExampleBtn(buttonX, buttonY, buttonWidth, buttonHeight);
        
        System.out.println("[faleUI] 主菜单按钮已创建，位置: (" + buttonX + ", " + buttonY + ")");
    }
    
    /**
     * 在TitleScreen渲染时渲染faleUI按钮
     * @param graphics GuiGraphics实例
     * @param mouseX 鼠标X坐标
     * @param mouseY 鼠标Y坐标
     * @param delta 时间增量
     * @param ci 回调信息
     */
    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(GuiGraphics graphics, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (faleUIButton != null) {
            faleUIButton.render(graphics, mouseX, mouseY, delta);
        }
    }
    
    // 注：由于Minecraft 1.21.11中mouseClicked方法签名发生变化，
    // 暂时移除鼠标事件处理以确保该示例写在您的模组中能正常启动
    // 这玩意好烦啊
    
    /*
    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void onMouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (faleUIButton != null) {
            // 让ExampleBtn处理鼠标点击事件
            if (faleUIButton.mouseClicked(mouseX, mouseY, button)) {
                cir.cancel(); // 取消后续的默认处理
                cir.setReturnValue(true); // 返回true表示事件已被处理
                System.out.println("[faleUI] 主菜单按钮点击事件已处理");
            }
        }
    }
    */
    // 这一个题目留给社区解决，如果您知道方案，请在 GitHub 上提交 Issues
}