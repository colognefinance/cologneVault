/* eslint-disable no-restricted-globals */

export const timeConverter = (timestamp) => {
  const a = new Date(timestamp * 1000);
  const months = [
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "May",
    "Jun",
    "Jul",
    "Aug",
    "Sep",
    "Oct",
    "Nov",
    "Dec",
  ];
  const year = a.getFullYear();
  const month = months[a.getMonth()];
  const date = a.getDate();
  const hour = a.getHours();
  const min = a.getMinutes();
  const sec = a.getSeconds();

  return `${date} ${month} ${year} ${hour}:${min}:${sec}`;
};

/**
 * Parse parameter from browser string by its name
 *
 * @param parameterName
 * @returns {*}
 */
export const findGetParameter = (parameterName) => {
  let result = null;
  let tmp = [];

  location.search
    .substr(1)
    .split("&")
    .forEach((item) => {
      tmp = item.split("=");
      if (tmp[0] === parameterName) {
        result = decodeURIComponent(tmp[1]);
      }
    });

  return result;
};
