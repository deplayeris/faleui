# 1 先导开发者指南

感谢你选择faleUI作为UI开发库，从这里开始，无论你是新手开发者，还是专业人群，本文档完全覆盖，助力您在您的模组的UI开发路上顺利前进。<br>
您还可以阅读源码给出的ExampleXXXX.java的示范，了解如何使用.
> [!NOTE]
> ⚠️ 注意：本文档所述的都是faleUI在1.21.11的版本的最新文档

## 🎯 先介绍

faleUI是一个基于Fabric的UI开发库，它提供了一套完整的UI绘制API，使得开发者能够快速创建默认或自定义的UI界面。<br>
他的设计遵循以下原则：
- 模块化：faleUI的每个功能都封装成一个模块，你可以根据需要选择使用哪些模块。
- 易于调用：faleUI的模块都有易于调用的方法Api，你也可以直接调用他们，缩短UI开发时间。

## 🚀 快速开始

### 环境准备

```java
// 所需依赖版本 - 这些是你项目中必需的库
// ⚠️ 新手注意：这些代码要放在你的build.gradle文件中
dependencies {
    minecraft "com.mojang:minecraft:1.21.11"          // Minecraft游戏本体
    mappings "net.fabricmc:yarn:1.21.11+build.1:v2"   // 代码映射文件，让代码更容易理解
    modImplementation "net.fabricmc:fabric-loader:0.15.+"  // Fabric模组加载器
    modImplementation "net.fabricmc.fabric-api:fabric-api:0.100.1+1.21.11"  // Fabric API
    
    // faleUI库依赖 - 这是最重要的部分！
    modImplementation "mod.deplayer:faleui:0.1d"       // faleUI渲染库
    // 或者如果是本地开发，可以使用：
    // modImplementation files("libs/faleui-x.xa+1.21.11.jar")
}
```

### 基础集成示例

```java
import mod.deplayer.faleui.FaleUIClient;      // faleUI主类
import net.fabricmc.api.ClientModInitializer;  // Fabric客户端初始化接口

// 这是你的模组客户端入口类
public class MyModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // 当游戏启动时会自动调用这个方法
        // 初始化faleUI系统 - 这是使用faleUI的第一步
        FaleUIClient.initialize();
        
        // 在控制台输出一条消息，确认集成成功
        // 💡 新手提示：可以在游戏启动时按F3+T查看控制台输出
        System.out.println("[MyMod] faleUI集成完成");
    }
}
```

## 🎨 核心API详解

### 渲染引擎 (RenderEngine)

#### 类结构分析
```java
/**
 * 线程安全的单例渲染引擎
 * 职责：提供统一的2D图形渲染接口
 * 
 * 对新手开发者解释：
 * - 单例模式：整个程序只有一个RenderEngine实例
 * - 线程安全：多个地方同时使用也不会出问题
 * - 静态方法getInstance()：获取这个唯一的实例
 */
public class RenderEngine {
    // 创建唯一的实例
    private static final RenderEngine INSTANCE = new RenderEngine();
    
    // 私有构造函数 - 防止别人用new RenderEngine()创建新实例
    private RenderEngine() {}
    
    // 获取唯一实例的公共方法
    public static RenderEngine getInstance() {
        return INSTANCE;
    }
}
```

#### 基础绘图方法

**矩形绘制**
```java
/**
 * 绘制纯色矩形
 * @param graphics GuiGraphics上下文
 * @param x 左上角X坐标
 * @param y 左上角Y坐标  
 * @param width 宽度（像素）
 * @param height 高度（像素）
 * @param color ARGB颜色值 (0xAARRGGBB)
 */
public void drawRectangle(GuiGraphics graphics, int x, int y, int width, int height, int color) {
    graphics.fill(x, y, x + width, y + height, color);
}

// 使用示例 - 这会在屏幕上画一个绿色矩形
RenderEngine.getInstance().drawRectangle(
    graphics,           // 渲染上下文 - 告诉程序在哪里画
    100,               // X坐标 - 距离左边100像素
    100,               // Y坐标 - 距离顶部100像素
    200,               // 宽度 - 矩形宽200像素
    150,               // 高度 - 矩形高150像素
    0xFF4CAF50        // 颜色 - 绿色 (FF=完全不透明, 4CAF50=绿色值)
);
```

**带边框矩形**
```java
/**
 * 绘制带边框的矩形
 * @param graphics GuiGraphics上下文
 * @param x 左上角X坐标
 * @param y 左上角Y坐标
 * @param width 宽度
 * @param height 高度
 * @param fillColor 填充颜色 (0为透明)
 * @param borderColor 边框颜色
 * @param borderWidth 边框宽度 (像素)
 */
public void drawBorderedRectangle(GuiGraphics graphics, int x, int y, int width, int height, 
                                int fillColor, int borderColor, int borderWidth) {
    // 填充区域计算
    if (fillColor != 0) {
        graphics.fill(x + borderWidth, y + borderWidth, 
                     x + width - borderWidth, y + height - borderWidth, fillColor);
    }
    
    // 边框绘制（四个矩形组成）
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

// 实际应用示例 - 画一个带蓝色填充和白色边框的矩形
RenderEngine.getInstance().drawBorderedRectangle(
    graphics,           // 渲染上下文
    50,                 // X坐标 - 距离左边50像素
    50,                 // Y坐标 - 距离顶部50像素
    300,                // 宽度 - 300像素宽
    200,                // 高度 - 200像素高
    0x802196F3,         // 填充颜色 - 半透明蓝色 (80=50%透明度)
    0xFFFFFFFF,         // 边框颜色 - 白色 (FF=完全不透明)
    3                   // 边框宽度 - 3像素粗
);
```

**线条绘制**
```java
/**
 * 绘制线条（支持粗细控制）
 * @param graphics GuiGraphics上下文
 * @param x1 起点X坐标
 * @param y1 起点Y坐标
 * @param x2 终点X坐标
 * @param y2 终点Y坐标
 * @param color 颜色值
 * @param thickness 线条粗细（像素）
 */
public void drawLine(GuiGraphics graphics, int x1, int y1, int x2, int y2, int color, int thickness) {
    if (thickness <= 1) {
        // 细线优化处理
        graphics.fill(x1, y1, x2 + 1, y2 + 1, color);
    } else {
        // 粗线使用矩形近似
        int dx = x2 - x1;
        int dy = y2 - y1;
        
        if (Math.abs(dx) >= Math.abs(dy)) {
            // 水平主导线条
            int minY = Math.min(y1, y2) - thickness / 2;
            int maxY = Math.max(y1, y2) + thickness / 2;
            graphics.fill(Math.min(x1, x2), minY, Math.max(x1, x2) + 1, maxY + 1, color);
        } else {
            // 垂直主导线条
            int minX = Math.min(x1, x2) - thickness / 2;
            int maxX = Math.max(x1, x2) + thickness / 2;
            graphics.fill(minX, Math.min(y1, y2), maxX + 1, Math.max(y1, y2) + 1, color);
        }
    }
}

// 使用场景示例 - 画一条红色的粗线
RenderEngine.getInstance().drawLine(
    graphics,           // 渲染上下文
    100, 100,           // 起点坐标 - 从(100,100)开始
    400, 200,           // 终点坐标 - 到(400,200)结束
    0xFFFF0000,         // 线条颜色 - 红色 (FF=不透明, FF0000=红色)
    5                   // 线条粗细 - 5像素宽
);
```

### UI组件系统

#### 基础组件架构 (UIBaseComponent)

```java
/**
 * UI组件抽象基类
 * 定义了所有UI组件必须实现的标准接口
 * 所有UI组件都应该继承此类，并实现必要的方法
 * 而且我们还内置了一些常规的用法，用于应对基本的的UI事件
 * 继承此类后，只需要实现必要的方法，就可以创建一个完整的UI组件
 * 但是，也只是一个组件，你需要在另一个创建使用类中创建这个组件的实例
 * 然后再进行一些Mixin注入处理，你才能完整的实现你想要达到的效果
 */
public abstract class UIBaseComponent {
    // 基础属性
    protected int x, y;                    // 组件位置
    protected int width, height;           // 组件尺寸
    protected boolean visible = true;      // 可见性状态
    protected boolean enabled = true;      // 启用状态
    protected boolean hovered = false;     // 悬停状态
    protected Component tooltip;           // 工具提示文本
    
    /**
     * 核心渲染方法
     * @param graphics 渲染上下文
     * @param mouseX 当前鼠标X坐标
     * @param mouseY 当前鼠标Y坐标
     * @param delta 时间增量（用于动画）
     */
    public abstract void render(GuiGraphics graphics, int mouseX, int mouseY, float delta);
    
    /**
     * 鼠标点击事件处理器 - 当用户点击鼠标时会调用
     * @param mouseX 鼠标点击X坐标 - 点击位置的水平坐标
     * @param mouseY 鼠标点击Y坐标 - 点击位置的垂直坐标
     * @param button 鼠标按键 - 0=左键，1=右键，2=中键
     * @return 是否消费了此事件 - true表示已处理，false表示未处理
     * 
     * 💡 新手提示：返回true表示"这件事我处理了，别再传给别人"
     *           返回false表示"我没处理，让别人试试"
     */
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false; // 默认不处理任何点击
    }
    
    /**
     * 悬停状态更新
     * @param mouseX 鼠标X坐标
     * @param mouseY 鼠标Y坐标
     */
    public void updateHoverState(int mouseX, int mouseY) {
        this.hovered = isHovered(mouseX, mouseY);
    }
    
    /**
     * 点击区域检测 - 判断鼠标是否在组件上面
     * @param mouseX 鼠标X坐标 - 鼠标的水平位置
     * @param mouseY 鼠标Y坐标 - 鼠标的垂直位置
     * @return 是否在组件范围内 - true表示鼠标在组件上，false表示不在
     * 
     * 💡 新手解释：这个方法检查鼠标坐标是否在组件的矩形区域内
     *           左边界 <= mouseX < 右边界 且 上边界 <= mouseY < 下边界
     */
    protected boolean isHovered(int mouseX, int mouseY) {
        // 检查X坐标：鼠标在组件左边界和右边界之间
        // 检查Y坐标：鼠标在组件上边界和下边界之间
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }
}
```

#### 按钮组件实现 (UIButton)

```java
/**
 * 交互式按钮组件
 * 支持点击、悬停、按下三种状态的视觉反馈
 * 如果要查看我们的示范，请查看我们的示例代码src/client/java/mod/deplayer/faleui/ExampleBtn.java
 * 这会更好的助你理解
 * 如果要在游戏中查看实际效果，请开发者去往src/client/resources/faleui.mixins.json
 * 照里面说的做,然后编译投入游戏运行就可以看到效果
 */
public class UIButton extends UIBaseComponent {
    private Component text;                           // 按钮显示文本
    private Runnable onClickAction;                   // 点击回调函数
    private boolean pressed = false;                  // 按下状态
    
    // 状态颜色配置
    private int normalColor = 0xFF404040;             // 正常态颜色
    private int hoverColor = 0xFF606060;              // 悬停态颜色
    private int pressedColor = 0xFF202020;            // 按下态颜色
    private int textColor = 0xFFFFFFFF;               // 文本颜色
    
    public UIButton(int x, int y, int width, int height, Component text) {
        super(x, y, width, height);
        this.text = text;
    }
    
    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        if (!visible) return;
        
        updateHoverState(mouseX, mouseY);
        
        // 状态颜色选择
        int backgroundColor = selectBackgroundColor();
        
        // 渲染背景
        RenderEngine.getInstance().drawRectangle(graphics, x, y, width, height, backgroundColor);
        
        // 渲染边框
        renderBorder(graphics);
        
        // 渲染文本
        if (text != null) {
            renderButtonText(graphics);
        }
    }
    
    private int selectBackgroundColor() {
        if (pressed) return pressedColor;
        if (hovered) return hoverColor;
        return normalColor;
    }
    
    private void renderBorder(GuiGraphics graphics) {
        // 根据悬停状态选择边框颜色
        int borderColor = hovered ? 0xFFAAAAAA : 0xFF888888;  // 悬停时亮一些
        
        // 绘制1像素边框 - 分别画四条线
        
        // 上边框：从左上角到右上角画一条横线
        graphics.fill(x, y, x + width, y + 1, borderColor);           
        
        // 下边框：从左下角到右下角画一条横线
        graphics.fill(x, y + height - 1, x + width, y + height, borderColor); 
        
        // 左边框：从左上角到左下角画一条竖线
        graphics.fill(x, y, x + 1, y + height, borderColor);          
        
        // 右边框：从右上角到右下角画一条竖线
        graphics.fill(x + width - 1, y, x + width, y + height, borderColor);  
    }
    
    private void renderButtonText(GuiGraphics graphics) {
        // 获取Minecraft实例和字体对象
        Minecraft minecraft = Minecraft.getInstance();
        Font font = minecraft.font;
        
        // 计算文本居中位置
        int textWidth = font.width(text);                    // 获取文本的实际宽度
        int textX = x + (width - textWidth) / 2;             // 水平居中：组件中心减去文本宽度的一半
        int textY = y + (height - 8) / 2;                    // 垂直居中：组件中心减去字体高度的一半(8像素)
        
        // 在计算好的位置绘制文本
        graphics.drawString(font, text, textX, textY, textColor);
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // 如果组件被禁用或不可见，不响应点击
        if (!enabled || !visible) return false;
        
        // 检查鼠标是否在组件上
        if (isHovered((int)mouseX, (int)mouseY)) {
            pressed = true;              // 标记为按下状态
            playClickSound();            // 播放点击音效
            return true;                 // 告诉系统事件已被处理
        }
        return false;                    // 鼠标不在组件上，不处理
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
    
    private void playClickSound() {
        Minecraft.getInstance().getSoundManager().play(
            SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F)
        );
    }
}
```


## 📊 API参考手册

### RenderEngine 方法索引

| 方法签名 | 功能描述 | 参数说明 |
|---------|---------|---------|
| `drawRectangle(GuiGraphics, int, int, int, int, int)` | 绘制纯色矩形 | x,y坐标;width,height尺寸;color颜色 |
| `drawBorderedRectangle(GuiGraphics, int, int, int, int, int, int, int)` | 绘制带边框矩形 | 前4个参数同上;fillColor填充色;borderColor边框色;borderWidth边框宽 |
| `drawLine(GuiGraphics, int, int, int, int, int, int)` | 绘制线条 | x1,y1起点;x2,y2终点;color颜色;thickness粗细 |
| `getInstance()` | 获取单例实例 | 静态方法，无需参数 |

### UIBaseComponent 核心方法

| 方法签名 | 返回类型 | 说明 |
|---------|---------|------|
| `render(GuiGraphics, int, int, float)` | void | 抽象方法，子类必须实现 |
| `mouseClicked(double, double, int)` | boolean | 鼠标点击事件，默认返回false |
| `mouseReleased(double, double, int)` | void | 鼠标释放事件 |
| `mouseDragged(double, double, int, double, double)` | void | 鼠标拖拽事件 |
| `keyPressed(int, int, int)` | boolean | 键盘按下事件 |
| `charTyped(char, int)` | boolean | 字符输入事件 |
| `updateHoverState(int, int)` | void | 更新悬停状态 |
| `isHovered(int, int)` | boolean | 检测是否悬停 |

### UIButton 特有方法

| 方法签名 | 功能 |
|---------|------|
| `setOnClick(Runnable)` | 设置点击回调函数 |
| `setText(Component)` | 设置按钮文本 |
| `setNormalColor(int)` | 设置正常状态颜色 |
| `setHoverColor(int)` | 设置悬停状态颜色 |
| `setPressedColor(int)` | 设置按下状态颜色 |
| `setTextColor(int)` | 设置文本颜色 |

## 😥 “灵异事件”解决

### 常见问题及解决方案

**问题1：组件不响应点击事件**
```java
// 检查清单：
// 1. 确保组件可见和启用
if (!component.isVisible() || !component.isEnabled()) {
    System.out.println("组件状态异常：visible=" + component.isVisible() + 
                      ", enabled=" + component.isEnabled());
}

// 2. 验证事件处理链
@Override
public boolean mouseClicked(double mouseX, double mouseY, int button) {
    boolean handled = super.mouseClicked(mouseX, mouseY, button);
    
    // 调试输出
    System.out.println("鼠标点击: (" + mouseX + ", " + mouseY + ") button=" + button);
    
    if (myButton.mouseClicked(mouseX, mouseY, button)) {
        System.out.println("按钮事件已处理");
        return true;
    }
    
    return handled;
}
```

**问题2：渲染顺序混乱**
```java
// 正确的渲染顺序
@Override
public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
    // 1. 背景层（最先渲染）
    renderBackground(graphics);
    
    // 2. UI组件层（中间渲染）
    updateHoverStates(mouseX, mouseY);
    renderComponents(graphics, mouseX, mouseY, delta);
    
    // 3. 覆盖层（最后渲染）
    renderOverlays(graphics);
    
    super.render(graphics, mouseX, mouseY, delta);
}
```

**问题3：颜色显示异常**
```java
// ARGB颜色格式验证
public static boolean isValidColor(int color) {
    int alpha = (color >> 24) & 0xFF;
    int red = (color >> 16) & 0xFF;
    int green = (color >> 8) & 0xFF;
    int blue = color & 0xFF;
    
    return alpha >= 0 && alpha <= 255 &&
           red >= 0 && red <= 255 &&
           green >= 0 && green <= 255 &&
           blue >= 0 && blue <= 255;
}

// 使用示例
int testColor = 0xFF4CAF50;
if (!isValidColor(testColor)) {
    System.err.println("无效的颜色值: " + String.format("0x%08X", testColor));
}
```

> Sorry<br>
> 由于本模组的作者也是新手开发者,所以文档大部分采自于AIGC生成,请自行判断<br>
> 不过我们尽量对新手开发者以及其他人群都做了相应的文档阅读人群一网打尽化,无论你是专业的开发者，还是新手开发者，都适合观看<br>
> 我们避免了底层化、盲代码的文档，这对于后面我们自身的开发和社区的使用体验都很友好