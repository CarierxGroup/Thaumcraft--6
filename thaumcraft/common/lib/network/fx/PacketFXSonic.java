/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraftforge.fml.client.FMLClientHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.Thaumcraft;
/*    */ import thaumcraft.client.fx.other.FXSonic;
/*    */ 
/*    */ public class PacketFXSonic
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketFXSonic, IMessage>
/*    */ {
/*    */   private int source;
/*    */   
/*    */   public PacketFXSonic() {}
/*    */   
/* 22 */   public PacketFXSonic(int source) { this.source = source; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 27 */   public void toBytes(ByteBuf buffer) { buffer.writeInt(this.source); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   public void fromBytes(ByteBuf buffer) { this.source = buffer.readInt(); }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(PacketFXSonic message, MessageContext ctx) {
/* 38 */     Entity p = Thaumcraft.proxy.getClientWorld().func_73045_a(message.source);
/* 39 */     if (p != null) {
/* 40 */       FXSonic fb = new FXSonic(Thaumcraft.proxy.getClientWorld(), p.field_70165_t, p.field_70163_u, p.field_70161_v, p, 10);
/*    */ 
/*    */       
/* 43 */       (FMLClientHandler.instance().getClient()).field_71452_i.func_78873_a(fb);
/*    */     } 
/* 45 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\fx\PacketFXSonic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */