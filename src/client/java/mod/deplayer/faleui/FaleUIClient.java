package mod.deplayer.faleui;

import net.fabricmc.api.ClientModInitializer;

/**
 * faleUI客户端初始化类
 * 负责初始化faleUI系统和注册相关组件
 */
public class FaleUIClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// 初始化faleUI渲染引擎
		System.out.println("[faleUI] 客户端初始化完成");
		System.out.println("[faleUI] 自定义主菜单已启用");
	}
}