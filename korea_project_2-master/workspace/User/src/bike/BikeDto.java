package bike;

public class BikeDto {
    private  String no,company, model, Year, Category, Power_HP, Bore_mm, Stroke_mm, Torque_Nm, Engine_type, Fuel_system, Cooling_system, Gearbox, Front_suspension, Rear_suspension, Front_tyre, Rear_tyre, Front_brakes, Rear_brakes, Compression_Ratio, Light, Displacement_ccm, Wheelbase_mm, Fuel_capacity_liters, Dry_weight_kg, Weight_incl_oil_gas_etc_kg, Overall_height_mm, Overall_length_mm, Overall_width_mm, Oil_capacity_liters, Seat_height_mm, Compression_Enumerator, Fuel_consumption_miles_gallon, Automatic_gearbox, Torque_Benchmark_RPM, Power_Benchmark_RPM = "";
    //검색용
    private String searchcategory,keyword;

    public String getSearchcategory() {
        return searchcategory;
    }

    public void setSearchcategory(String searchcategory) {
        this.searchcategory = searchcategory;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {

        this.model = model;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getPower_HP() {
        return Power_HP;
    }

    public void setPower_HP(String power_HP) {
        Power_HP = power_HP;
    }

    public String getBore_mm() {
        return Bore_mm;
    }

    public void setBore_mm(String bore_mm) {
        Bore_mm = bore_mm;
    }

    public String getStroke_mm() {
        return Stroke_mm;
    }

    public void setStroke_mm(String stroke_mm) {
        Stroke_mm = stroke_mm;
    }

    public String getTorque_Nm() {
        return Torque_Nm;
    }

    public void setTorque_Nm(String torque_Nm) {
        Torque_Nm = torque_Nm;
    }

    public String getEngine_type() {
        return Engine_type;
    }

    public void setEngine_type(String engine_type) {
        Engine_type = engine_type;
    }

    public String getFuel_system() {
        return Fuel_system;
    }

    public void setFuel_system(String fuel_system) {
        Fuel_system = fuel_system;
    }

    public String getCooling_system() {
        return Cooling_system;
    }

    public void setCooling_system(String cooling_system) {
        Cooling_system = cooling_system;
    }

    public String getGearbox() {
        return Gearbox;
    }

    public void setGearbox(String gearbox) {
        Gearbox = gearbox;
    }

    public String getFront_suspension() {
        return Front_suspension;
    }

    public void setFront_suspension(String front_suspension) {
        Front_suspension = front_suspension;
    }

    public String getRear_suspension() {
        return Rear_suspension;
    }

    public void setRear_suspension(String rear_suspension) {
        Rear_suspension = rear_suspension;
    }

    public String getFront_tyre() {
        return Front_tyre;
    }

    public void setFront_tyre(String front_tyre) {
        Front_tyre = front_tyre;
    }

    public String getRear_tyre() {
        return Rear_tyre;
    }

    public void setRear_tyre(String rear_tyre) {
        Rear_tyre = rear_tyre;
    }

    public String getFront_brakes() {
        return Front_brakes;
    }

    public void setFront_brakes(String front_brakes) {
        Front_brakes = front_brakes;
    }

    public String getRear_brakes() {
        return Rear_brakes;
    }

    public void setRear_brakes(String rear_brakes) {
        Rear_brakes = rear_brakes;
    }

    public String getCompression_Ratio() {
        return Compression_Ratio;
    }

    public void setCompression_Ratio(String compression_Ratio) {
        Compression_Ratio = compression_Ratio;
    }

    public String getLight() {
        return Light;
    }

    public void setLight(String light) {
        Light = light;
    }

    public String getDisplacement_ccm() {
        return Displacement_ccm;
    }

    public void setDisplacement_ccm(String displacement_ccm) {
        Displacement_ccm = displacement_ccm;
    }

    public String getWheelbase_mm() {
        return Wheelbase_mm;
    }

    public void setWheelbase_mm(String wheelbase_mm) {
        Wheelbase_mm = wheelbase_mm;
    }

    public String getFuel_capacity_liters() {
        return Fuel_capacity_liters;
    }

    public void setFuel_capacity_liters(String fuel_capacity_liters) {
        Fuel_capacity_liters = fuel_capacity_liters;
    }

    public String getDry_weight_kg() {
        return Dry_weight_kg;
    }

    public void setDry_weight_kg(String dry_weight_kg) {
        Dry_weight_kg = dry_weight_kg;
    }

    public String getWeight_incl_oil_gas_etc_kg() {
        return Weight_incl_oil_gas_etc_kg;
    }

    public void setWeight_incl_oil_gas_etc_kg(String weight_incl_oil_gas_etc_kg) {
        Weight_incl_oil_gas_etc_kg = weight_incl_oil_gas_etc_kg;
    }

    public String getOverall_height_mm() {
        return Overall_height_mm;
    }

    public void setOverall_height_mm(String overall_height_mm) {
        Overall_height_mm = overall_height_mm;
    }

    public String getOverall_length_mm() {
        return Overall_length_mm;
    }

    public void setOverall_length_mm(String overall_length_mm) {
        Overall_length_mm = overall_length_mm;
    }

    public String getOverall_width_mm() {
        return Overall_width_mm;
    }

    public void setOverall_width_mm(String overall_width_mm) {
        Overall_width_mm = overall_width_mm;
    }

    public String getOil_capacity_liters() {
        return Oil_capacity_liters;
    }

    public void setOil_capacity_liters(String oil_capacity_liters) {
        Oil_capacity_liters = oil_capacity_liters;
    }

    public String getSeat_height_mm() {
        return Seat_height_mm;
    }

    public void setSeat_height_mm(String seat_height_mm) {
        Seat_height_mm = seat_height_mm;
    }

    public String getCompression_Enumerator() {
        return Compression_Enumerator;
    }

    public void setCompression_Enumerator(String compression_Enumerator) {
        Compression_Enumerator = compression_Enumerator;
    }

    public String getFuel_consumption_miles_gallon() {
        return Fuel_consumption_miles_gallon;
    }

    public void setFuel_consumption_miles_gallon(String fuel_consumption_miles_gallon) {
        Fuel_consumption_miles_gallon = fuel_consumption_miles_gallon;
    }

    public String getAutomatic_gearbox() {
        return Automatic_gearbox;
    }

    public void setAutomatic_gearbox(String automatic_gearbox) {
        Automatic_gearbox = automatic_gearbox;
    }

    public String getTorque_Benchmark_RPM() {
        return Torque_Benchmark_RPM;
    }

    public void setTorque_Benchmark_RPM(String torque_Benchmark_RPM) {
        Torque_Benchmark_RPM = torque_Benchmark_RPM;
    }

    public String getPower_Benchmark_RPM() {
        return Power_Benchmark_RPM;
    }

    public void setPower_Benchmark_RPM(String power_Benchmark_RPM) {
        Power_Benchmark_RPM = power_Benchmark_RPM;
    }
}
