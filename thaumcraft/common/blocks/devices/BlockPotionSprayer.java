/*    */ package thaumcraft.common.blocks.devices;
/*    */ 
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.Thaumcraft;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.blocks.IBlockEnabled;
/*    */ import thaumcraft.common.blocks.IBlockFacing;
/*    */ 
/*    */ public class BlockPotionSprayer
/*    */   extends BlockTCDevice
/*    */   implements IBlockFacing, IBlockEnabled
/*    */ {
/*    */   public BlockPotionSprayer() {
/* 21 */     super(Material.field_151573_f, thaumcraft.common.tiles.devices.TilePotionSprayer.class, "potion_sprayer");
/* 22 */     func_149672_a(SoundType.field_185852_e);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 29 */     if (world.field_72995_K) return true; 
/* 30 */     player.openGui(Thaumcraft.instance, 21, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 36 */   public int func_180651_a(IBlockState state) { return 0; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockPotionSprayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */