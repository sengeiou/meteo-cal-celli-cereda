/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.cellicereda.meteocal.businesslogic;

import it.polimi.cellicereda.meteocal.entities.Event;
import it.polimi.cellicereda.meteocal.entities.Place;
import java.util.Date;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;

/**
 *
 * @author stefano
 */
public class ForecastManagerTest {
    
    private ForecastManager fm;
    private Event event = new Event();
    
    @Before
    public void setUp() {
        fm = new ForecastManager();
        fm.em = mock(EntityManager.class);
        fm.lm = mock(LocationManager.class);

        //create a place entity
        Place moscow = new Place();
        moscow.setId((long) 524901);
        moscow.setName("Moscow");
        moscow.setLongitude(37.615555);
        moscow.setLatitude(55.75222);

        //and a related event
        event.setStartDate(new Date());
        event.setEndDate(new Date(new Date().getTime() + 1 * 60 * 60 * 1000));
        event.setEventLocation(moscow);

        //and return it when the forecast manager asks it to the location manager
        Mockito.when(fm.lm.getPlaceByID((long) 524901)).thenReturn(moscow);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void DownloadNewForecastForEvent() {
        Assert.assertNotNull(fm.downloadNewForecastForEvent(event));
    }
    
    @Test
    public void isGoodWeather() {
        assertTrue(fm.isGoodWeather(800));
        assertTrue(fm.isGoodWeather(801));
        assertTrue(fm.isGoodWeather(802));
        assertTrue(fm.isGoodWeather(803));
        assertTrue(fm.isGoodWeather(804));
        
        assertFalse(fm.isGoodWeather(200));
        assertFalse(fm.isGoodWeather(232));
        assertFalse(fm.isGoodWeather(300));
        assertFalse(fm.isGoodWeather(321));
        assertFalse(fm.isGoodWeather(500));
        assertFalse(fm.isGoodWeather(511));
        assertFalse(fm.isGoodWeather(520));
        assertFalse(fm.isGoodWeather(531));
        assertFalse(fm.isGoodWeather(600));
        assertFalse(fm.isGoodWeather(622));
        assertFalse(fm.isGoodWeather(701));
        assertFalse(fm.isGoodWeather(781));
    }
}