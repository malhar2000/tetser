import tartan.smarthome.resources.StaticTartanStateEvaluator;
import tartan.smarthome.resources.iotcontroller.IoTValues;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestVacantDoorState {
    private Map<String, Object> mockDataVacant;
    private StringBuffer mockLogVacant;

    /**
     * Create vacant mock data
     */
    private void initVacant() {
        mockDataVacant = new HashMap<String, Object>();
        mockLogVacant = new StringBuffer();
        mockDataVacant.put(IoTValues.AWAY_TIMER, false);
        mockDataVacant.put(IoTValues.LIGHT_STATE, true);
        mockDataVacant.put(IoTValues.PROXIMITY_STATE, false);
        mockDataVacant.put(IoTValues.ALARM_STATE, false);
        mockDataVacant.put(IoTValues.HUMIDIFIER_STATE, false);
        mockDataVacant.put(IoTValues.HEATER_STATE, false);
        mockDataVacant.put(IoTValues.CHILLER_STATE, false);
        mockDataVacant.put(IoTValues.ALARM_ACTIVE, false);
        mockDataVacant.put(IoTValues.HVAC_MODE, "");
        mockDataVacant.put(IoTValues.ALARM_PASSCODE, "");
        mockDataVacant.put(IoTValues.GIVEN_PASSCODE, "");
        mockDataVacant.put(IoTValues.TEMP_READING, 1);
        mockDataVacant.put(IoTValues.TARGET_TEMP, 1);
    }

    @Test
    @DisplayName("VacantWithClosedDoorIsClosed")
    public void testVacantDoorClosed() {
        // Init vacant house
        initVacant();

        // With door closed
        mockDataVacant.put(IoTValues.DOOR_STATE, false);
        StaticTartanStateEvaluator evaluator = new StaticTartanStateEvaluator();
        Map<String, Object> vacantOut = evaluator.evaluateState(mockDataVacant, mockLogVacant);

        // assert that door is closed
        assertEquals(false, vacantOut.get(IoTValues.DOOR_STATE));
    }

    @Test
    @DisplayName("VacantWithOpenDoorIsClosed")
    public void testVacantDoorOpen() {
        // Init vacant house
        initVacant();

        // With door open
        mockDataVacant.put(IoTValues.DOOR_STATE, true);
        StaticTartanStateEvaluator evaluator = new StaticTartanStateEvaluator();
        Map<String, Object> vacantOut = evaluator.evaluateState(mockDataVacant, mockLogVacant);

        // assert that door is closed
        assertEquals(false, vacantOut.get(IoTValues.DOOR_STATE));
    }

}