/**
 *
 * har - HAR file reader, writer and viewer
 * Copyright (c) 2014, Sandeep Gupta
 * 
 * http://sangupta.com/projects/har
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.supretest.api.dto.definition.parse.har.model;

public class HarHeader {
	
	public String name;
	
	public String value;
	
	public String comment;
	
	@Override
	public String toString() {
		return "[Header: " + this.name + "=" + this.value + "]";
	}

	@Override
	public int hashCode() {
		if(this.name == null) {
			return -1;
		}
		
		return this.name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof HarHeader)) {
			return false;
		}
		
		if(this.name == null) {
			return false;
		}
		
		HarHeader harHeader = (HarHeader) obj;
		return this.name.equals(harHeader.name);
	}
	
}
