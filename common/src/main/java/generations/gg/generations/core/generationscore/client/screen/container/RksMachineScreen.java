package generations.gg.generations.core.generationscore.client.screen.container;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.C2STogglePacket;
import generations.gg.generations.core.generationscore.world.container.RksMachineContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;

import java.util.Objects;

public class RksMachineScreen extends AbstractContainerScreen<RksMachineContainer> {
	private static final ResourceLocation TEXTURE = GenerationsCore.id("textures/gui/container/rks_machine.png");

	private final Button button;

	public RksMachineScreen(RksMachineContainer handler, Inventory inventory, Component title) {
		super(handler, inventory, title);
		button = Button.builder(Component.literal("Start"), new Button.OnPress() {
			@Override
			public void onPress(Button button) {
				GenerationsCore.getImplementation().getNetworkManager().sendPacketToServer(new C2STogglePacket(handler.getRksMachine().getBlockPos()));
			}
		}).bounds(0, 0, 41, 13).build();
	}

	public void init() {
		super.init();
		this.addRenderableWidget(button);
		this.button.setPosition(this.leftPos + 109, this.topPos + 62);
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}


	public void render(GuiGraphics matrices, int mouseX, int mouseY, float delta) {
		this.renderBackground(matrices);
		this.renderBg(matrices, delta, mouseX, mouseY);
		super.render(matrices, mouseX, mouseY, delta);
		this.renderTooltip(matrices, mouseX, mouseY);}

	protected void renderBg(GuiGraphics matrices, float delta, int mouseX, int mouseY) {
		int i = this.leftPos;
		int j = (this.height - this.imageHeight) / 2;
		matrices.blit(TEXTURE, i, j, 0, 0, this.imageWidth, this.imageWidth);

		if (this.menu.isWeaving()) {
			int k = (this.menu).getBurnProgress(22);
			matrices.blit(TEXTURE, i + 89, j + 34, 176, 0, k + 1, 16);
		}
	}
}
