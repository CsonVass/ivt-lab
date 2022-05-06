package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryTorpedoStore; 
   private TorpedoStore secondaryorpedoStore;   

  @BeforeEach
  public void init(){
     this.primaryTorpedoStore = mock(TorpedoStore.class);  
    this.secondaryorpedoStore = mock(TorpedoStore.class);   
 
     this.ship = new GT4500(primaryTorpedoStore, secondaryorpedoStore);
    
    }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(secondaryorpedoStore.isEmpty()).thenReturn(true);
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryorpedoStore.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(primaryTorpedoStore, times(1)).fire(1); 
    verify(secondaryorpedoStore, times(0)).fire(2); 

    // Assert
    assertEquals(true, result);

  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(secondaryorpedoStore.isEmpty()).thenReturn(false);
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    
    verify(primaryTorpedoStore, times(1)).fire(1); 

    // Assert
    assertEquals(true, result);
  }

}
