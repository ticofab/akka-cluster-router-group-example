# Akka ClusterRouterGroup Example

Example usage of Akka Cluster's ClusterRouterGroup functionality.

Start the master (on port 2551 by default) with

```$xslt
sbt run
```

and then connect / disconnect more workers to it by starting 

```$xslt
sbt -DPORT=[PORT] -DROLES.1="worker" run
```

where `[PORT]` is a port different than 2551.

## License

    Copyright 2017 Fabio Tiriticco - Fabway

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
