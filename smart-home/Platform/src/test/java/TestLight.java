import tartan.smarthome.resources.StaticTartanStateEvaluator;
import tartan.smarthome.resources.iotcontroller.IoTValues;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLight {
    private Map<String, Object> mockDataVaccant;
    private StringBuffer mockLogVaccant;
    private Map<String, Object> mockDataUnvaccant;
    private StringBuffer mockLogUnvaccant;

    public void initTest() {
        // this will be run before each test
        mockDataVaccant = new HashMap<String, Object>();
        mockLogVaccant = new StringBuffer();
        mockDataUnvaccant = new HashMap<String, Object>();
        mockLogUnvaccant = new StringBuffer();
    }

    private void initDataVaccant() {
        mockDataVaccant.put(IoTValues.DOOR_STATE, false);
        mockDataVaccant.put(IoTValues.AWAY_TIMER, false);
        // Light is on
        mockDataVaccant.put(IoTValues.LIGHT_STATE, true);
        // House is vaccant
        mockDataVaccant.put(IoTValues.PROXIMITY_STATE, false);
        mockDataVaccant.put(IoTValues.ALARM_STATE, false);
        mockDataVaccant.put(IoTValues.HUMIDIFIER_STATE, false);
        mockDataVaccant.put(IoTValues.HEATER_STATE, false);
        mockDataVaccant.put(IoTValues.CHILLER_STATE, false);
        mockDataVaccant.put(IoTValues.ALARM_ACTIVE, false);
        mockDataVaccant.put(IoTValues.HVAC_MODE, "");
        mockDataVaccant.put(IoTValues.ALARM_PASSCODE, "");
        mockDataVaccant.put(IoTValues.GIVEN_PASSCODE, "");
        mockDataVaccant.put(IoTValues.TEMP_READING, 1);
        mockDataVaccant.put(IoTValues.TARGET_TEMP, 1);
    }

    private void initDataLightUnvaccant() {
        mockDataUnvaccant.put(IoTValues.DOOR_STATE, false);
        mockDataUnvaccant.put(IoTValues.AWAY_TIMER, false);
        // Light is on
        mockDataUnvaccant.put(IoTValues.LIGHT_STATE, true);
        // House is unvaccant
        mockDataUnvaccant.put(IoTValues.PROXIMITY_STATE, true);
        mockDataUnvaccant.put(IoTValues.ALARM_STATE, false);
        mockDataUnvaccant.put(IoTValues.HUMIDIFIER_STATE, false);
        mockDataUnvaccant.put(IoTValues.HEATER_STATE, false);
        mockDataUnvaccant.put(IoTValues.CHILLER_STATE, false);
        mockDataUnvaccant.put(IoTValues.ALARM_ACTIVE, false);
        mockDataUnvaccant.put(IoTValues.HVAC_MODE, "");
        mockDataUnvaccant.put(IoTValues.ALARM_PASSCODE, "");
        mockDataUnvaccant.put(IoTValues.GIVEN_PASSCODE, "");
        mockDataUnvaccant.put(IoTValues.TEMP_READING, 1);
        mockDataUnvaccant.put(IoTValues.TARGET_TEMP, 1);
    }

    @Test
    public void testLightVaccant() {
        // test for vaccant
        initTest();
        initDataVaccant();
        StaticTartanStateEvaluator vaccant = new StaticTartanStateEvaluator();
        Map<String, Object> vaccantOut = vaccant.evaluateState(mockDataVaccant, mockLogVaccant);
        // assert if light is turned off
        assertEquals(false, vaccantOut.get(IoTValues.LIGHT_STATE));
    }

    @Test
    public void testLightUnvaccant() {
        // test for unvaccant
        initTest();
        initDataLightUnvaccant();
        StaticTartanStateEvaluator unvaccant = new StaticTartanStateEvaluator();
        Map<String, Object> unvaccantOut = unvaccant.evaluateState(mockDataUnvaccant, mockLogUnvaccant);
        // assert if light is still on
        assertEquals(true, unvaccantOut.get(IoTValues.LIGHT_STATE));
    }

}