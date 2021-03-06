/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2015-03-26 20:30:19 UTC)
 * on 2015-05-25 at 03:43:46 UTC 
 * Modify at your own risk.
 */

package com.virtualef.pgj.service.locationService.model;

/**
 * Model definition for LocationDto.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the locationService. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class LocationDto extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<GeoLocationDto> geoLocation;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double idAgent;

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<GeoLocationDto> getGeoLocation() {
    return geoLocation;
  }

  /**
   * @param geoLocation geoLocation or {@code null} for none
   */
  public LocationDto setGeoLocation(java.util.List<GeoLocationDto> geoLocation) {
    this.geoLocation = geoLocation;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public LocationDto setId(java.lang.Integer id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getIdAgent() {
    return idAgent;
  }

  /**
   * @param idAgent idAgent or {@code null} for none
   */
  public LocationDto setIdAgent(java.lang.Double idAgent) {
    this.idAgent = idAgent;
    return this;
  }

  @Override
  public LocationDto set(String fieldName, Object value) {
    return (LocationDto) super.set(fieldName, value);
  }

  @Override
  public LocationDto clone() {
    return (LocationDto) super.clone();
  }

}
