import tartan.smarthome.resources.StaticTartanStateEvaluator;
import tartan.smarthome.resources.iotcontroller.IoTValues;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDehumidifer {
    private Map<String, Object> mockData;
    private StringBuffer mockLog;

    /**
     * Create mock data
     */
    private void initData() {
        mockData = new HashMap<String, Object>();
        mockLog = new StringBuffer();
        mockData.put(IoTValues.DOOR_STATE, false);
        mockData.put(IoTValues.AWAY_TIMER, false);
        mockData.put(IoTValues.LIGHT_STATE, true);
        mockData.put(IoTValues.PROXIMITY_STATE, true);
        mockData.put(IoTValues.ALARM_STATE, true);
        mockData.put(IoTValues.CHILLER_STATE, false);
        mockData.put(IoTValues.ALARM_ACTIVE, false);
        mockData.put(IoTValues.HVAC_MODE, "");
        mockData.put(IoTValues.ALARM_PASSCODE, "");
        mockData.put(IoTValues.GIVEN_PASSCODE, "");
        mockData.put(IoTValues.TEMP_READING, 1);
        mockData.put(IoTValues.TARGET_TEMP, 1);

    }

    @Test
    @DisplayName("Test if Dehumidifer is disabled automatically when heater is on")
    public void testVacantDoorClosed() {
        //TEST R12: Humidifer and Heater cannot run simataneously. When both the heating and humidifer is on, the humidifer should be disabled automatically.

        // ARRANGE
        initData();
        mockData.put(IoTValues.HEATER_STATE, true);
        mockData.put(IoTValues.HUMIDIFIER_STATE, true);

        // ACT
        StaticTartanStateEvaluator evaluator = new StaticTartanStateEvaluator();
        Map<String, Object> stateOut = evaluator.evaluateState(mockData, mockLog);

        // ASSERT
        assertEquals(false, stateOut.get(IoTValues.HUMIDIFIER_STATE));
    }

}