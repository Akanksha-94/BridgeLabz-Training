
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class UnitTest {
	@Test
	public void Test_Deposit_ValidAmount() {
		Program acc=new Program(100);
		acc.deposit(50);
//		assertTrue(depositedVal(500));
		assertEquals(150,acc.getBalance());
	}
	@Test
	public void Test_Deposit_NegativeAmount() {
		
		Program acc=new Program(100);
		acc.deposit(-500);
		Exception exception=assertThrows(IllegalArgumentException.class,()->acc.deposit(-10));
		assertEquals("Deposited amount cannot be negative",exception.getMessage());
	}
	@Test
	public void Test_Withdraw_ValidAmount() {
		Program acc = new Program(100);
		acc.withdraw(50);
		assertEquals(50,acc.getBalance());
	}
	
	@Test
	public void Test_Withdraw_InsufficientFunds() {
		Program acc=new Program(100);
		acc.withdraw(500);
		Exception exception=assertThrows(IllegalArgumentException.class,()->acc.withdraw(500));
		assertEquals("Insufficient Balance",exception.getMessage());
	
	}
	
}
