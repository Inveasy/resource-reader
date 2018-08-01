/*
 * Copyright 2018 Guillaume Gravetot
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.inveasy.resources;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ResourceReader
{
	private List<String[]> splittedLines;
	
	public ResourceReader(String resourceName, int neededElementsPerLine) throws IOException, URISyntaxException
	{
		splittedLines = new ArrayList<>();
		
		Enumeration<URL> urls = ClassLoader.getSystemClassLoader().getResources(resourceName);
		
		while(urls.hasMoreElements())
		{
			List<String> lines = Files.readAllLines(Paths.get(urls.nextElement().toURI()), Charset.defaultCharset());
			
			for(String line : lines)
			{
				String[] splitted = line.split("\\s");
				
				if(splitted.length != neededElementsPerLine)
					throw new IOException("Resource file is malformed, contains " + splitted.length + " elements per line, " + neededElementsPerLine + " required");
				
				splittedLines.add(splitted);
			}
		}
	}
	
	public List<String[]> getSplittedLines()
	{
		return splittedLines;
	}
}
