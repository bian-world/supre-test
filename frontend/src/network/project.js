import {baseGet} from "@/network/base-network";
import {getCurrentProjectID} from "@/common/js/utils";

export function getProject(projectId, callback) {
  return projectId ? baseGet('/project/get/' + projectId, callback) : {};
}

export function getCurrentProject(callback) {
  return getProject(getCurrentProjectID(), callback);
}

export function getSubProjects(projectId, callback) {
  return projectId ? baseGet('/project/getSubProjects/' + projectId, (response) => {
    if (callback) {
      callback(response.data);
    }
  }):[];
}
