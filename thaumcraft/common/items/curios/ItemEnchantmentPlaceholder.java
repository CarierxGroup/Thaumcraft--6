/*    */ package thaumcraft.common.items.curios;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.util.text.translation.I18n;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.items.ItemTCBase;
/*    */ 
/*    */ 
/*    */ public class ItemEnchantmentPlaceholder
/*    */   extends ItemTCBase
/*    */ {
/*    */   public ItemEnchantmentPlaceholder() {
/* 21 */     super("enchanted_placeholder", new String[0]);
/* 22 */     func_77625_d(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/* 29 */   public boolean func_77636_d(ItemStack stack) { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   public boolean func_77616_k(ItemStack stack) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public EnumRarity func_77613_e(ItemStack stack) { return EnumRarity.RARE; }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
/* 47 */     super.func_77624_a(stack, worldIn, tooltip, flagIn);
/* 48 */     tooltip.add(TextFormatting.ITALIC + "" + TextFormatting.DARK_AQUA + I18n.func_74838_a("item.enchanted_placeholder.text"));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 53 */   public boolean func_150897_b(IBlockState blockIn) { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 58 */   public boolean canHarvestBlock(IBlockState state, ItemStack stack) { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 63 */   public int getHarvestLevel(ItemStack stack, String toolClass, EntityPlayer player, IBlockState blockState) { return 99; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\curios\ItemEnchantmentPlaceholder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */