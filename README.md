# CompressionLibraryInJava
This is a java API for compression and decompression using table lookup for substrings of same characters
The load.properties is a file which has a dictionary of frequently occuring substrings
The encode method is a static method of Compressor class which returns a Map which is a Dictionary containing keywords occuring with their comma separated list of indices in the text
The Map along with the compressed string is sent over the network 
The Decode method is a static method of Compressor class which uses the information in the map to return the original uncompressed string
