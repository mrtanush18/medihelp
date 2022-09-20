import axios from "axios";

const HOSPITAL_BASE_REST_API_URL = "http://localhost:9090";

// HTTP Methods

class HospitalService {
  getAllHospitals() {
    return axios.get(HOSPITAL_BASE_REST_API_URL + "/" + "hospital");
  }

  createHospital() {
    return axios.post(HOSPITAL_BASE_REST_API_URL + "/" + "HospitalLists");
  }

  getHospitalById(hospitalId) {
    return axios.get(HOSPITAL_BASE_REST_API_URL + "/" + hospitalId);
  }

  updateHospital(hospitalId, hospital) {
    return axios.put(
      HOSPITAL_BASE_REST_API_URL +
        "/" +
        "updateHospitalListById" +
        "/" +
        hospitalId
    );
  }

  deleteHospital(hospitalId) {
    return axios.delete(
      HOSPITAL_BASE_REST_API_URL + "/" + "HospitalLists" + "/" + hospitalId
    );
  }
}

export default new HospitalService();
