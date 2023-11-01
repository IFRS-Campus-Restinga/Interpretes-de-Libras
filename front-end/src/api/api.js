import axios from "axios";

export default function api() {
  axios.create({
    baseURL: "localhost:8080",
  });
}
