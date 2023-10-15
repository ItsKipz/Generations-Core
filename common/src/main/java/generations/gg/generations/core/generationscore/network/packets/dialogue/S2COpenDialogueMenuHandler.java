package generations.gg.generations.core.generationscore.network.packets.dialogue;

import generations.gg.generations.core.generationscore.client.screen.dialgoue.display.DialogueScreen;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

public class S2COpenDialogueMenuHandler implements ClientNetworkPacketHandler<S2COpenDialogueMenuPacket> {
    public static final S2COpenDialogueMenuHandler INSTANCE = new S2COpenDialogueMenuHandler();

    @Override
    public void handle(@NotNull S2COpenDialogueMenuPacket packet) {
        Minecraft.getInstance().setScreen(new DialogueScreen(packet.closable));
    }
}
