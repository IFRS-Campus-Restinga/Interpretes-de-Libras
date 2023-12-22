import axios from "axios";
//Hello
export default function api() {
  axios.create({
    baseURL: "localhost:8080",
  });
}
