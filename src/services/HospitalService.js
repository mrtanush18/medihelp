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

  getHospitalById(id) {
    return axios.get(HOSPITAL_BASE_REST_API_URL + "/" + id);
  }

  updateHospital(id) {
    return axios.put(
      HOSPITAL_BASE_REST_API_URL + "/" + "updateHospitalListById" + id
    );
  }

  deleteHospital(id) {
    return axios.delete(
      HOSPITAL_BASE_REST_API_URL + "/" + "HospitalLists" + id
    );
  }
}

export default new HospitalService();
