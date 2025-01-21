import { api } from "../../utils/api";

export async function getVeterinarians(){
  try {
    const result = await api.get("/veterinarians/")
    console.log(result.data);
    return result.data;
  } catch (error) {
    throw error;
  }
}