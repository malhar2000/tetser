import tartan.smarthome.resources.StaticTartanStateEvaluator;
import tartan.smarthome.resources.iotcontroller.IoTValues;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class TestAwayTimerExpires{
    private Map<String, Object> mockDataAwayTimerExpires;
    private StringBuffer mockLogHistory;
    private StaticTartanStateEvaluator evaluator;


    @BeforeEach
    void initData(){
        mockDataAwayTimerExpires = new HashMap<String, Object>();
        mockLogHistory = new StringBuffer();
        evaluator = new StaticTartanStateEvaluator();

        // door open
        mockDataAwayTimerExpires.put(IoTValues.DOOR_STATE, false);
        // away timer set
        mockDataAwayTimerExpires.put(IoTValues.AWAY_TIMER, false);
        // lights on
        mockDataAwayTimerExpires.put(IoTValues.LIGHT_STATE, false);
        mockDataAwayTimerExpires.put(IoTValues.PROXIMITY_STATE, false);
        // alarm disable
        mockDataAwayTimerExpires.put(IoTValues.ALARM_STATE, false);
        mockDataAwayTimerExpires.put(IoTValues.HUMIDIFIER_STATE, false);
        mockDataAwayTimerExpires.put(IoTValues.HEATER_STATE, false);
        mockDataAwayTimerExpires.put(IoTValues.CHILLER_STATE, false);
        mockDataAwayTimerExpires.put(IoTValues.ALARM_ACTIVE, false);
        mockDataAwayTimerExpires.put(IoTValues.HVAC_MODE, "");
        mockDataAwayTimerExpires.put(IoTValues.ALARM_PASSCODE, "");
        mockDataAwayTimerExpires.put(IoTValues.GIVEN_PASSCODE, "");
        mockDataAwayTimerExpires.put(IoTValues.TEMP_READING, 1);
        mockDataAwayTimerExpires.put(IoTValues.TARGET_TEMP, 1);


    }

    @Test
    public void testAwayTimerExpires(){

        mockDataAwayTimerExpires.put(IoTValues.DOOR_STATE, true);
        // away timer set
        mockDataAwayTimerExpires.put(IoTValues.AWAY_TIMER, true);
        // lights on
        mockDataAwayTimerExpires.put(IoTValues.LIGHT_STATE, true);
        mockDataAwayTimerExpires.put(IoTValues.ALARM_STATE, false);

        Map<String, Object> result = evaluator.evaluateState(mockDataAwayTimerExpires, mockLogHistory);

        assertAll(
                ()->assertEquals(false, result.get(IoTValues.DOOR_STATE)),
                ()->assertEquals(false, result.get(IoTValues.AWAY_TIMER)),
                ()->assertEquals(false, result.get(IoTValues.LIGHT_STATE)),
                ()->assertEquals(true, result.get(IoTValues.ALARM_STATE))
        );
    }

    @Test
    public void testAwayTimerNotExpires(){

        mockDataAwayTimerExpires.put(IoTValues.DOOR_STATE, true);
        mockDataAwayTimerExpires.put(IoTValues.AWAY_TIMER, false);
        mockDataAwayTimerExpires.put(IoTValues.LIGHT_STATE, true);
        mockDataAwayTimerExpires.put(IoTValues.PROXIMITY_STATE, true);

        Map<String, Object> result = evaluator.evaluateState(mockDataAwayTimerExpires, mockLogHistory);

        assertAll(
                ()->assertEquals(true, result.get(IoTValues.DOOR_STATE), "Failed door_state"),
                ()->assertEquals(false, result.get(IoTValues.AWAY_TIMER), "Failed away_timer"),
                ()->assertEquals(true, result.get(IoTValues.LIGHT_STATE), "Failed light_state"),
                ()->assertEquals(false, result.get(IoTValues.ALARM_STATE), "Failed alarm_state")
        );
    }

}